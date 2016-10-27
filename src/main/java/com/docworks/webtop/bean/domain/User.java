/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.bean.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @author liuwei
 * @version 1.0
 */
public class User implements java.io.Serializable {

    private String objectId;            // ObjectId
    @NotBlank
    private String userName;            // 用户名
    @NotBlank
    private String userAddress;         // 用户邮箱
    @NotBlank
    private String userLoginName;       // 登录名
    private Date modifyDate;            // 修改时间
    private Integer clientCapability;   // 客户端行为

    public User() {
        super();
    }

    @JsonProperty("r_object_id")
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @JsonProperty("user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty("user_address")
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @JsonProperty("user_login_name")
    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    @JsonProperty("r_modify_date")
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @JsonProperty("client_capability")
    public Integer getClientCapability() {
        return clientCapability;
    }

    public void setClientCapability(Integer clientCapability) {
        this.clientCapability = clientCapability;
    }

    @Override
    public String toString() {
        return "User{" +
                "objectId='" + objectId + '\'' +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userLoginName='" + userLoginName + '\'' +
                ", modifyDate=" + modifyDate +
                ", clientCapability=" + clientCapability +
                '}';
    }
}
