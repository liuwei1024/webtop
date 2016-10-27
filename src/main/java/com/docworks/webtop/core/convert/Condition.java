/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.core.convert;

/**
 * @author liuwei
 * @version 1.0
 */
public interface Condition {

    /**
     * 查询条件Where cause
     * @return String
     */
    String toCondition();

    /**
     * 排序条件ORDER BY
     * @return String
     */
    String toOrder();

    /**
     * 分页条件
     * @return String
     */
    String toPagination();
}
