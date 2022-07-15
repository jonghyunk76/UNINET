package com.yni.fta.common.beans.pointcut;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

/**
 * 특정 매소드 또는 클래스에 Advisor가 적용되도록 정의하는 클래스
 * 
 * @author jonghyun3.kim
 *
 */
public class InterfacePointcut extends StaticMethodMatcherPointcut {

    private static Log log = LogFactory.getLog(InterfacePointcut.class);

    public boolean matches(Method method, Class cls) {
        if (cls.getName().startsWith("com.yni.fta.common.beans.target")) {
            if (log.isDebugEnabled())
                log.debug(cls.getName() + "." + method.getName() + " matched name");

            return true;
        }

        return false;
    }

}
