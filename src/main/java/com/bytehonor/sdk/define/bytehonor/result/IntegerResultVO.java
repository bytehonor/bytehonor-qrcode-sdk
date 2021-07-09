package com.bytehonor.sdk.define.bytehonor.result;

/**
 * @author lijianqiang
 *
 */
public class IntegerResultVO {

    private Integer result;
    
    public static IntegerResultVO of(Integer result) {
        return new IntegerResultVO(result);
    }

    public IntegerResultVO(Integer result) {
        this.result = result;
    }

    public IntegerResultVO() {
        this(null);
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

}
