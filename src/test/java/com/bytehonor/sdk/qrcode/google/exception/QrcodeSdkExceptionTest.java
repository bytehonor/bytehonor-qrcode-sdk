package com.bytehonor.sdk.qrcode.google.exception;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QrcodeSdkExceptionTest {

    @Test
    public void testGetCode() {
        QrcodeSdkException se = new QrcodeSdkException("test");

        assertTrue("testGetCode should return 'true'", se != null);
    }

}
