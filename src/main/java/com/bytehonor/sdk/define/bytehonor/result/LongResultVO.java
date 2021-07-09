package com.bytehonor.sdk.define.bytehonor.result;

/**
 * @author lijianqiang
 *
 */
public class LongResultVO {

    private Long result;
    
    public static LongResultVO of(Long result) {
        return new LongResultVO(result);
    }

    public LongResultVO(Long result) {
        this.result = result;
    }

    public LongResultVO() {
        this(null);
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

}
