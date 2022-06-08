package com.bytehonor.sdk.qrcode.google.model;

import java.util.Objects;

public class QrcodeRequest {

    private String content;
    private int width;
    private int height;
    private String destPath;
    private String logoPath;

    public static QrcodeRequest make(String content, String destPath) {
        return make(content, destPath, null);
    }

    public static QrcodeRequest make(String content, String destPath, String logoPath) {
        Objects.requireNonNull(content, "content");
        Objects.requireNonNull(destPath, "destPath");
        return new QrcodeRequest(content, 300, 300, destPath, logoPath);
    }

    public QrcodeRequest(String content, int width, int height, String destPath, String logoPath) {
        this.content = content;
        this.width = width;
        this.height = height;
        this.destPath = destPath;
        this.logoPath = logoPath;
    }

    public QrcodeRequest() {
        this(null, 300, 300, null, null);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

}
