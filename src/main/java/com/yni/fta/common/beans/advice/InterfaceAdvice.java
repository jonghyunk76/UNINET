package com.yni.fta.common.beans.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InterfaceAdvice implements MethodInterceptor {
    private static Log log = LogFactory.getLog(InterfaceAdvice.class);

    public Object invoke(MethodInvocation invocation) throws Throwable {
        String className = invocation.getThis().getClass().getName();
        String methodName = invocation.getMethod().getName();

        if (log.isDebugEnabled()) {
            log.debug(className + "." + methodName + "() Start!!");
        }

        Object retVal = invocation.proceed();

        if (log.isDebugEnabled()) {
            log.debug(className + "." + methodName + "() End!!");
        }

        return retVal;
    }

}
