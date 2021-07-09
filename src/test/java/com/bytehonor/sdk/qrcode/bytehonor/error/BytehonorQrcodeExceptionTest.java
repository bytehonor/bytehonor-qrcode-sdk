package com.bytehonor.sdk.qrcode.bytehonor.error;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bytehonor.sdk.qrcode.bytehonor.exception.BytehonorQrcodeException;

public class BytehonorQrcodeExceptionTest {

    @Test
    public void testGetCode() {
        BytehonorQrcodeException se = new BytehonorQrcodeException("test");

        assertTrue("testGetCode should return 'true'", se != null);
    }

}
