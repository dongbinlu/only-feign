package com.only.feign.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * bean定义的后置 注册处理器
 */
public class RemoteCallBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {


    private String scannerPackages;

    public void setScannerPackages(String scannerPackages) {
        this.scannerPackages = scannerPackages;
    }

    /**
     * 执行时机：所有的bean定义信息将要被加载到容器中，Bean实例还没有被初始化。
     *
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathRemoteCallScanner classPathRemoteCallScanner = new ClassPathRemoteCallScanner(registry);
        // 会通过父类的scan方法调用到我们自己的子类ClassPathRemoteCallScanner的doScan方法。
        // 把我们定义的接口bean定义扫描到容器中
        classPathRemoteCallScanner.scan(scannerPackages);
    }

    /**
     * 此方法中可以看到容器中已经添加了bean定义
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
