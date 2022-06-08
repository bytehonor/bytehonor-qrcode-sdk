package com.bytehonor.sdk.qrcode.google.exception;

/**
 * @author lijianqiang
 *
 */
public class QrcodeSdkException extends RuntimeException {

    private static final long serialVersionUID = 3469151926340920201L;

    public QrcodeSdkException() {
        super();
    }

    public QrcodeSdkException(String message) {
        super(message);
    }

    public QrcodeSdkException(Exception cause) {
        super(cause);
    }
}
