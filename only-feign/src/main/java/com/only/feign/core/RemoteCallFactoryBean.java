package com.only.feign.core;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class RemoteCallFactoryBean<T> extends AbstractDiscoveryClientSupport implements FactoryBean<T> {

    private Class<T> targetClass;

    public RemoteCallFactoryBean(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public T getObject() throws Exception {

        RemoteCallProxy remoteCallProxy = new RemoteCallProxy(getDiscoveryClient(), targetClass);
        return (T) Proxy.newProxyInstance(targetClass.getClassLoader(),
                new Class[]{targetClass},
                remoteCallProxy);
    }

    @Override
    public Class<?> getObjectType() {
        return targetClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
