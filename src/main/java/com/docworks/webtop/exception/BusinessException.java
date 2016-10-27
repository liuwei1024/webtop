/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.exception;

import com.docworks.webtop.bean.enums.message.MessageCode;

/**
 * 业务逻辑错误异常处理类
 * @author liuwei
 * @version 1.0
 */
public class BusinessException extends BaseException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message, MessageCode messageCode, Object... params) {
        super(message, messageCode, params);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, MessageCode messageCode, Object... params) {
        super(message, cause, messageCode, params);
    }
}
