/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.exception;

import com.docworks.webtop.bean.enums.message.MessageCode;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常基础类
 * @author liuwei
 * @version 1.0
 */
public class BaseException extends RuntimeException {

    private String messageCode;
    private Object[] params;

    // --------------- Constructors ---------------

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, MessageCode messageCode, Object... params) {
        super(message);
        this.messageCode = messageCode.value();
        this.params = params;
    }

    public BaseException(String message, Throwable cause, MessageCode messageCode, Object... params) {
        super(message, cause);
        this.messageCode = messageCode.value();
        this.params = params;
    }

    /**
     * 获取异常Stack Trace信息
     * @param cause {@link Throwable}
     * @return String
     */
    public static String getStackTrace(Throwable cause) {
        StringWriter writer = new StringWriter();
        cause.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    // --------------- Getter & Setter ---------------

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
