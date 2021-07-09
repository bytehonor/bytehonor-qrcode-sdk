package com.bytehonor.sdk.define.bytehonor.result;

/**
 * @author lijianqiang
 *
 */
public class BooleanResultVO {

    private Boolean result;

    public static BooleanResultVO of(Boolean result) {
        return new BooleanResultVO(result);
    }

    public BooleanResultVO(Boolean result) {
        this.result = result;
    }

    public BooleanResultVO() {
        this(false);
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

}
