package com.bytehonor.sdk.qrcode.bytehonor.exception;

/**
 * @author lijianqiang
 *
 */
public class BytehonorQrcodeException extends RuntimeException {

    private static final long serialVersionUID = 3469151926340920201L;

    public BytehonorQrcodeException() {
        super();
    }

    public BytehonorQrcodeException(String message) {
        super(message);
    }

    public BytehonorQrcodeException(Exception cause) {
        super(cause);
    }
}
