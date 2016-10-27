/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.bean.enums;

/**
 * @author liuwei
 * @version 1.0
 */
public enum UserStatus {
    ACTIVE(0),
    INACTIVE(1),
    LOCKED(2),
    LOCKED_INACTIVE(4);

    private int value;

    UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
