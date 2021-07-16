package com.bytehonor.sdk.qrcode.bytehonor.builder;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.bytehonor.sdk.qrcode.bytehonor.model.QrcodeRequest;

public class GoogleQrcodeBuilderTest {

    @Test
    public void testBuildWithLogo() {
        String destPath = "D:/data/testBuildWithLogo.png";
        String logoPath = "D:/data/wechatpay_logo_150.jpg";
        QrcodeRequest reuqest = QrcodeRequest.make("hello world", destPath, logoPath);
        GoogleQrcodeBuilder.build(reuqest);

        File file = new File(destPath);
        assertTrue("testBuildWithLogo", file.exists());
    }

    @Test
    public void testBuild() {
        String destPath = "D:/data/testBuild.png";
        QrcodeRequest reuqest = QrcodeRequest.make("hello world", destPath);
        GoogleQrcodeBuilder.build(reuqest);

        File file = new File(destPath);
        assertTrue("testBuild", file.exists());
    }

}
