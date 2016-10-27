/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.service;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfUser;
import com.documentum.fc.common.DfException;
import com.docworks.dctm.query.core.DctmTemplate;
import com.docworks.webtop.bean.domain.User;
import com.docworks.webtop.bean.dto.PageDto;
import com.docworks.webtop.bean.enums.message.UserMessageCode;
import com.docworks.webtop.core.convert.Condition;
import com.docworks.webtop.exception.BaseException;
import com.docworks.webtop.exception.BusinessException;
import com.docworks.webtop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuwei
 * @version 1.0
 */
@Service
public class UserService {

    @Autowired
    private DctmTemplate dctmTemplate;

    public PageDto<User> findAll(IDfSession dfSession, Condition condition) {
        String dql = "SELECT r_object_id, user_name, user_address, user_login_name, r_modify_date, client_capability FROM dm_user " +
                "WHERE " + condition.toCondition() +
                condition.toOrder() +
                condition.toPagination();

        String cntDql = "SELECT COUNT(*) AS dw_cnt FROM dm_user WHERE " + condition.toCondition();

        int cnt = dctmTemplate.queryForObject(cntDql, dfSession, "dw_cnt", Integer.class);
        List<User> list = dctmTemplate.queryForList(dql, dfSession, new UserMapper());

        return new PageDto<>(cnt, list);
    }

    public void create(IDfSession dfSession, User user) {
        try {
            // 验证用户是否重复
            IDfUser dfUser = dfSession.getUserByLoginName(user.getUserLoginName(), null);
            if (dfUser != null) {
                throw new BusinessException(null, UserMessageCode.MSG_USER_DUPLICATE);
            }

            //

        } catch (DfException e) {
            throw new BaseException(e.getMessage(), e);
        }

    }
}
