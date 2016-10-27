/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.bean.dto;

import java.util.List;

/**
 * @author liuwei
 * @version 1.0
 */
public class PageDto<T> {
    private Integer totalElements;
    private List<T> content;

    public PageDto() {
        super();
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public PageDto(Integer totalElements, List<T> content) {

        this.totalElements = totalElements;
        this.content = content;
    }
}
