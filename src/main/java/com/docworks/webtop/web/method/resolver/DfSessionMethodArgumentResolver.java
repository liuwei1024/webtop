/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.web.method.resolver;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfLoginInfo;
import com.docworks.webtop.web.method.annotation.DfSession;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Documentum DFC IDfSession注解方法参数Resolver
 * <p>扩展Spring MVC的{@link HandlerMethodArgumentResolver}接口，
 * 添加了支持DCTM的DfSession方法参数注解功能。</p>
 * @author liuwei
 * @version 1.0
 */
public class DfSessionMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /** 
	  * 请求中token的参数名，默认为token，你也可以修改为accessToken或access_token。
	  * 不建议在此处直接修改，请在application.properties配置文件中修改。
	  */
    private String tokenParamName = "token";

    public void setTokenParamName(String tokenParamName) {
        this.tokenParamName = tokenParamName;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(DfSession.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 默认从HttpRequest中获取参数名为token的参数值
        String strToken = nativeWebRequest.getParameter(tokenParamName);

        // 基本思路为：
        // 1.通过上一步获取的token，从token中解析出用户名（应该是用户登录名 user_login_name）
        // 2.使用超级管理员的权限为该用户分配一个DfSession对象
        // 3.返回该DfSession

        // 注：这里直接解析token，不需要对token进行合法校验，你可以把校验token的逻辑写到Spring的拦截器（Interceptor）里面
        // 因为Web应用接收到请求之后，首先会进入拦截器，如果能进入到这里，说明拦截器已经校验过token，token是合法的。


        // 这里为了方便测试，通过硬编码方式获取了用户的DfSession，请不要在开发环境或生产环境中直接使用，请根据上述的基本思路添加实现。
        IDfSession dfSession = getDfSession();
        return dfSession;
    }

    /**
     * 获取用户DfSession对象
     * 这个方法为测试方法，请不要在开发环境或生产环境中使用。
     * @return
     * @throws DfException
     */
    public IDfSession getDfSession() throws DfException {
        IDfClientX dfClientX = new DfClientX();
        IDfClient dfClient = dfClientX.getLocalClient();

        if (dfSessionMgr == null) {
            dfSessionMgr = dfClient.newSessionManager();

            IDfLoginInfo loginInfo = dfClientX.getLoginInfo();
            loginInfo.setUser("liuwei");
            loginInfo.setPassword("123456");

            dfSessionMgr.setIdentity("DMS", loginInfo);
        }
        return dfSessionMgr.getSession("DMS");
    }
    private static IDfSessionManager dfSessionMgr = null;
}
