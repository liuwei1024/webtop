/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.bean.vo;

/**
 * @author liuwei
 * @version 1.0
 */
public class ColumnVo {

    private String value;       // 字段值
    private String name;        // 字段名称
    private String type;        // 字段类型
    private String predicate;   // 谓词（查询类型：支持EQUAL，LIKE，NOT）

    public ColumnVo() {
        super();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPredicate() {
        return predicate;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }
}
