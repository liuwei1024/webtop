/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.bean.vo;

import java.util.List;

/**
 * @author liuwei
 * @version 1.0
 */
public class PageVo {
    private Integer page;
    private Integer length;
    private List<ColumnVo> columns;
    private List<OrderVo> orders;

    public PageVo() {
        super();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<ColumnVo> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnVo> columns) {
        this.columns = columns;
    }

    public List<OrderVo> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderVo> orders) {
        this.orders = orders;
    }
}
