/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.bean.dto;

import java.io.Serializable;

/**
 * @author liuwei
 * @version 1.0
 */
public class ResponseDto implements Serializable {
    public String code;
    public String message;
    public Object data;

    public ResponseDto() {
        super();
    }

    public ResponseDto(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
