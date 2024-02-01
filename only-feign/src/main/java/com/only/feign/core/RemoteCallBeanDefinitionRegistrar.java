package com.only.feign.core;

import com.only.feign.anno.EnableRemoteCall;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 往容器中导入bean定义
 * 用于扫描容器中标注了@RemoteCall注解
 */
public class RemoteCallBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        // 解析EnableRemoteCall注解
        AnnotationAttributes enableRemoteCallAttr = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableRemoteCall.class.getName()));

        if (enableRemoteCallAttr != null) {
            // beanName-> com.only.feign.FeignApplication#RemoteCallBeanDefinitionRegistrar#0
            registerBeanDefinitions(enableRemoteCallAttr, registry, generateBaseBeanName(importingClassMetadata, 0));
        }

    }

    private String generateBaseBeanName(AnnotationMetadata importingClassMetadata, int index) {
        return importingClassMetadata.getClassName() + "#" + RemoteCallBeanDefinitionRegistrar.class.getSimpleName() + "#" + index;
    }

    private void registerBeanDefinitions(AnnotationAttributes enableRemoteCallAttr, BeanDefinitionRegistry registry, String beanName) {

        // Bean定义建造者获取Bean定义 -> RemoteCallBeanDefinitionRegistryPostProcessor
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RemoteCallBeanDefinitionRegistryPostProcessor.class);

        // 定义扫描的包路径
        List<String> scannerPackages = Arrays.asList(enableRemoteCallAttr.getStringArray("scannerPackages"));
        // 给Bean定义的属性赋值
        builder.addPropertyValue("scannerPackages", StringUtils.collectionToCommaDelimitedString(scannerPackages));

        // 往容器中注册bean定义 com.only.feign.FeignApplication#RemoteCallBeanDefinitionRegistrar#0->RemoteCallBeanDefinitionRegistryPostProcessor
        registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
    }
}
