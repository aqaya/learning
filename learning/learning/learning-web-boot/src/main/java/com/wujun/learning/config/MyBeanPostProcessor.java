package com.wujun.learning.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("wujun".equals(beanName)){
            System.out.println("postProcessBeforeInitialization bean = [" + bean + "], beanName = [" + beanName + "]");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("wujun".equals(beanName)){
            System.out.println("postProcessAfterInitialization bean = [" + bean + "], beanName = [" + beanName + "]");
        }
        return bean;
    }
}
