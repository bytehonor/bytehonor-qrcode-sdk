package com.bytehonor.sdk.qrcode.google.cache;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BufferedImageCache {

    private static final Map<String, BufferedImage> CACHE = new ConcurrentHashMap<String, BufferedImage>(32);

    public static BufferedImage get(String key) {
        if (key == null) {
            return null;
        }
        return CACHE.get(key);
    }

    public static void put(String key, BufferedImage image) {
        if (key == null || image == null) {
            return;
        }
        CACHE.put(key, image);
    }
}
