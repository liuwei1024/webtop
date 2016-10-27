/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.bean.enums.message;

/**
 * @author liuwei
 * @version 1.0
 */
public enum HttpMessageCode implements MessageCode {

    HTTP_OK,
    HTTP_BAD_REQUEST,
    HTTP_INTERNAL_SERVER_ERROR;

    @Override
    public String value() {
        return this.toString();
    }
}
