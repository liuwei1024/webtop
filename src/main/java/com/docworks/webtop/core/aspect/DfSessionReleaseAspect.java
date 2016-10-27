/**
 * Copyright 2016 (c) Shanghai TY Technology Co.,Ltd. All Rights Reserved.
 */
package com.docworks.webtop.core.aspect;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * DCTM DFC IDfSession对象释放Aspect
 * <p>这个Aspect作用于Service层的方法，当Service层的方法执行完毕后，
 * 自动释放IDfSession对象，回收资源。</p>
 * @author liuwei
 * @version 1.0
 */
@Component
@Aspect
public class DfSessionReleaseAspect {

    /**
     * Aspect切点，切入到Service层的所有方法。
     */
    @Pointcut("execution(* com.docworks.webtop.service.*.*(..))")
    public void pointCut() {}

    /**
     * Aspect后置通知，当Service层的方法执行完毕后，执行该方法
     * @param joinPoint JoinPoint
     */
    @After("pointCut()")
    public void afterAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg != null && arg instanceof IDfSession) {
                // 释放DfSession
                IDfSession dfSession = (IDfSession) arg;
                IDfSessionManager dfSessionMgr = dfSession.getSessionManager();
                dfSessionMgr.release(dfSession);
                break;
            }
        }
    }
}
