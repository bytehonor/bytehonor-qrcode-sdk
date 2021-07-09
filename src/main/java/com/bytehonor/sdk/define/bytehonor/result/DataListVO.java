package com.bytehonor.sdk.define.bytehonor.result;

import java.util.List;

/**
 * @author lijianqiang
 *
 * @param <T>
 */
public class DataListVO<T> {

    private Integer total;

    private List<T> list;

    public static <T> DataListVO<T> of(List<T> list) {
        return of(list, list != null ? list.size() : 0);
    }

    public static <T> DataListVO<T> of(List<T> list, Integer total) {
        DataListVO<T> vo = new DataListVO<T>();
        vo.setList(list);
        vo.setTotal(total);
        return vo;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
