package com.view;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
public class PageParameter implements Serializable {

    private int pageSize = 10;

    private int pageNo = 1;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
