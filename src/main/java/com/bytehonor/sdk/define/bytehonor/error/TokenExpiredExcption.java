package com.bytehonor.sdk.define.bytehonor.error;

import com.bytehonor.sdk.define.bytehonor.code.StandardCode;

public class TokenExpiredExcption extends StandardException {

    private static final long serialVersionUID = 4735132729826163119L;

    public TokenExpiredExcption() {
        super(StandardCode.TOKEN_EXPIRED, "TOKEN_EXPIRED");
    }
}
