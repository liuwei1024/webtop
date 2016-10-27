/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.mapper;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfTime;
import com.docworks.dctm.query.core.RowMapper;
import com.docworks.webtop.bean.domain.User;

/**
 * @author liuwei
 * @version 1.0
 */
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(IDfCollection coll, int i) throws DfException {
        User user = new User();
        user.setObjectId(coll.getString("r_object_id"));
        user.setUserName(coll.getString("user_name"));
        user.setUserAddress(coll.getString("user_address"));
        user.setUserLoginName(coll.getString("user_login_name"));
        IDfTime modifyDate = coll.getTime("r_modify_date");
        user.setModifyDate(modifyDate != null ? modifyDate.getDate() : null);
        user.setClientCapability(coll.getInt("client_capability"));
        return user;
    }
}
