package com.bytehonor.sdk.define.bytehonor.error;

import com.bytehonor.sdk.define.bytehonor.code.StandardCode;

public class ParameterExcption extends StandardException {

    private static final long serialVersionUID = 4735132729826163119L;

    public ParameterExcption() {
        super(StandardCode.BAD_REQUEST, "BAD_REQUEST");
    }

    public ParameterExcption(String message) {
        super(StandardCode.BAD_REQUEST, "BAD_REQUEST_" + message);
    }
}
