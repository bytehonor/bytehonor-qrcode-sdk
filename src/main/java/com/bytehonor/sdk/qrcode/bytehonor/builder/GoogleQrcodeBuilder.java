package com.bytehonor.sdk.qrcode.bytehonor.builder;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;

import com.bytehonor.sdk.qrcode.bytehonor.cache.BufferedImageCache;
import com.bytehonor.sdk.qrcode.bytehonor.constant.QrcodeConstants;
import com.bytehonor.sdk.qrcode.bytehonor.exception.BytehonorQrcodeException;
import com.bytehonor.sdk.qrcode.bytehonor.model.QrcodeRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class GoogleQrcodeBuilder {
    // 图片宽度的一般
    private static final int IMAGE_WIDTH = QrcodeConstants.LOGO_IMAGE_WIDTH;
    private static final int IMAGE_HEIGHT = QrcodeConstants.LOGO_IMAGE_HEIGHT;
    private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
    private static final int FRAME_WIDTH = QrcodeConstants.LOGO_FRAME_WIDTH;

    private static final String FORMAT = "png";

    private static final String UTF_8 = "UTF-8";

    // 二维码写码器
    private static MultiFormatWriter WRITER = new MultiFormatWriter();

    public static void build(QrcodeRequest request) {
        Objects.requireNonNull(request, "request");
        if (request.getLogoPath() != null) {
            buildWithLogo(request);
        } else {
            buildWithoutLogo(request);
        }
    }

    public static void buildWithLogo(QrcodeRequest request) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(request.getContent(), "content");
        Objects.requireNonNull(request.getDestPath(), "destPath");
        Objects.requireNonNull(request.getLogoPath(), "logoPath");
        try {
            ImageIO.write(
                    genBarcode(request.getContent(), request.getWidth(), request.getHeight(), request.getLogoPath()),
                    FORMAT, new File(request.getDestPath()));
        } catch (Exception e) {
            throw new BytehonorQrcodeException(e.getMessage());
        }
    }

    public static void buildWithoutLogo(QrcodeRequest request) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(request.getContent(), "content");
        Objects.requireNonNull(request.getDestPath(), "destPath");
        try {
            /*
             * 定义二维码的参数
             */
            Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
            // 设置二维码字符编码
            hint.put(EncodeHintType.CHARACTER_SET, UTF_8);
            // 设置二维码纠错等级
            hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            // 设置二维码边距
            hint.put(EncodeHintType.MARGIN, 2);

            // 开始生成二维码
            BitMatrix matrix = WRITER.encode(request.getContent(), BarcodeFormat.QR_CODE, request.getWidth(),
                    request.getHeight(), hint);
            // 导出到指定目录
            MatrixToImageWriter.writeToPath(matrix, FORMAT, new File(request.getDestPath()).toPath());
        } catch (Exception e) {
            throw new BytehonorQrcodeException(e);
        }
    }

    private static BufferedImage genBarcode(String content, int width, int height, String logoPath)
            throws WriterException, IOException {
        // 读取源图像
        BufferedImage logoImage = BufferedImageCache.get(logoPath);
        if (logoImage == null) {
            logoImage = scale(logoPath, IMAGE_WIDTH, IMAGE_HEIGHT, true);
            BufferedImageCache.put(logoPath, logoImage);
        }
        int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
        for (int i = 0; i < logoImage.getWidth(); i++) {
            for (int j = 0; j < logoImage.getHeight(); j++) {
                srcPixels[i][j] = logoImage.getRGB(i, j);
            }
        }

        // 设置二维码的纠错级别 编码
        Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
        hint.put(EncodeHintType.CHARACTER_SET, UTF_8);
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hint.put(EncodeHintType.MARGIN, 2);

        // 生成二维码
        BitMatrix matrix = WRITER.encode(content, BarcodeFormat.QR_CODE, width, height, hint);

        // 二维矩阵转为一维像素数组
        int halfW = matrix.getWidth() / 2;
        int halfH = matrix.getHeight() / 2;
        int[] pixels = new int[width * height];

        for (int y = 0; y < matrix.getHeight(); y++) {
            for (int x = 0; x < matrix.getWidth(); x++) {
                // 读取图片
                if (x > halfW - IMAGE_HALF_WIDTH && x < halfW + IMAGE_HALF_WIDTH && y > halfH - IMAGE_HALF_WIDTH
                        && y < halfH + IMAGE_HALF_WIDTH) {
                    pixels[y * width + x] = srcPixels[x - halfW + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];
                }
                // 在图片四周形成边框
                else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH
                        && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH + IMAGE_HALF_WIDTH + FRAME_WIDTH)
                        || (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                                && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH
                                && y < halfH + IMAGE_HALF_WIDTH + FRAME_WIDTH)
                        || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                                && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH
                                && y < halfH - IMAGE_HALF_WIDTH + FRAME_WIDTH)
                        || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                                && y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH
                                && y < halfH + IMAGE_HALF_WIDTH + FRAME_WIDTH)) {
                    pixels[y * width + x] = 0xfffffff;
                } else {
                    // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                    pixels[y * width + x] = matrix.get(x, y) ? 0xff000000 : 0xfffffff;
                }
            }
        }

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.getRaster().setDataElements(0, 0, width, height, pixels);

        return image;
    }

    /**
     * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标
     * 
     * @param filePath  源文件地址
     * @param height    目标高度
     * @param width     目标宽度
     * @param hasFiller 比例不对时是否需要补白：true为补白; false为不补白;
     * @throws IOException
     */
    private static BufferedImage scale(String filePath, int height, int width, boolean hasFiller) throws IOException {
        double ratio = 0.0; // 缩放比例
        File file = new File(filePath);
        BufferedImage src = ImageIO.read(file);
        Image destImage = src.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
        // 计算比例
        if ((src.getHeight() > height) || (src.getWidth() > width)) {
            if (src.getHeight() > src.getWidth()) {
                ratio = Double.valueOf(height) / src.getHeight();
            } else {
                ratio = Double.valueOf(width) / src.getWidth();
            }
            AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
            destImage = op.filter(src, null);
        }
        if (hasFiller) {// 补白
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphic = image.createGraphics();
            graphic.setColor(Color.white);
            graphic.fillRect(0, 0, width, height);
            if (width == destImage.getWidth(null)) {
                graphic.drawImage(destImage, 0, (height - destImage.getHeight(null)) / 2, destImage.getWidth(null),
                        destImage.getHeight(null), Color.white, null);
            } else {
                graphic.drawImage(destImage, (width - destImage.getWidth(null)) / 2, 0, destImage.getWidth(null),
                        destImage.getHeight(null), Color.white, null);
            }
            graphic.dispose();
            destImage = image;
        }
        return (BufferedImage) destImage;
    }
}
