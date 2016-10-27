/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.exception;

import com.docworks.webtop.bean.enums.message.MessageCode;

/**
 * 错误的请求异常处理。
 * @author liuwei
 * @version 1.0
 */
public class BadRequestException extends BaseException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message, Throwable cause, MessageCode messageCode, Object[] params) {
        super(message, cause, messageCode, params);
    }
}
