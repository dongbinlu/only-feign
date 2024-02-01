package com.only.feign.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

@Slf4j
public class ClassPathRemoteCallScanner extends ClassPathBeanDefinitionScanner {

    private Class<? extends RemoteCallFactoryBean> remoteCallFactoryBeanClass = RemoteCallFactoryBean.class;

    public ClassPathRemoteCallScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        /**
         * 调用父类ClassPathBeanDefinitionScanner来进行扫描
         */
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        if (beanDefinitions.isEmpty()) {
            log.warn("没有对应的RemoteCall找到");
        } else {
            processBeanDefinition(beanDefinitions);
        }
        return beanDefinitions;
    }

    private void processBeanDefinition(Set<BeanDefinitionHolder> beanDefinitions) {
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder holder : beanDefinitions) {
            //获取我们的bean定义
            definition = (GenericBeanDefinition) holder.getBeanDefinition();
            //获取我们的beanclass的名称
            String beanClassName = definition.getBeanClassName();

            // 修改具体的beanclass，修改为FactoryBean,用来控制这个bean到底生成那个对象
            definition.setBeanClass(remoteCallFactoryBeanClass);
            // 将接口beanclass 通过构造方法传入
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
            definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        }
    }


}
