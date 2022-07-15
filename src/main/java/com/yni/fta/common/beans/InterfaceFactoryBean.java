package com.yni.fta.common.beans;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import kr.yni.frame.exception.FrameException;

/**
 * interface 실행하기 위한 인터스턴 생성 클래스
 * 
 * @author jonghyun3.kim
 *
 */
public class InterfaceFactoryBean implements FactoryBean, BeanFactoryAware, InitializingBean {

    private boolean singleton = true;

    private Object instance;

    private String[] interceptorNames;

    private Object target;

    private BeanFactory beanFactory;

    public void setBeanFactory(BeanFactory factory) throws BeansException {
        this.beanFactory = factory;
    }

    public void setInterceptorNames(String[] interceptorNames) {
        this.interceptorNames = interceptorNames;
    }

    /**
     * 인터페이스 배치를 수행할 target클래스명 지정
     * 
     * @param target
     */
    public void setTarget(Object target) {
        this.target = target;
    }

    public void afterPropertiesSet() throws Exception {
        if (this.target == null) {
            throw new FrameException("Target 클래스 이름이 등록되지 않았습니다.");
        }

        if (this.interceptorNames == null && this.interceptorNames.length == 0) {
            instance = target;
        } else {
            ProxyFactory pf = new ProxyFactory();
            pf.setTarget(target);

            for (int i = 0; i < interceptorNames.length; i++) {
                Advisor advisor = getAdvisor(interceptorNames[i]);

                if (advisor != null) {
                    pf.addAdvisors(advisor);
                }
            }

            instance = pf.getProxy();
        }
    }

    private Advisor getAdvisor(String interceptorName) {
        Object advObject = beanFactory.getBean(interceptorName);

        if (advObject instanceof Advisor) {
            return (Advisor) advObject;
        } else if (advObject instanceof Advice) {
            return new DefaultPointcutAdvisor((Advice) advObject);
        } else {
            return null;
        }
    }

    public Object getObject() throws Exception {
        return this.instance;
    }

    @SuppressWarnings("rawtypes")
    public Class getObjectType() {
        if (instance != null) {
            return instance.getClass();
        }

        return null;
    }

    public boolean isSingleton() {
        return this.singleton;
    }

}
