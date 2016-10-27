/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.bean.enums.message;

/**
 * @author liuwei
 * @version 1.0
 */
public enum UserMessageCode implements MessageCode {

    /** 用户不存在 */
    MSG_USER_NOT_FOUND,

    /** 用户重复 */
    MSG_USER_DUPLICATE;

    @Override
    public String value() {
        return this.toString();
    }
}
