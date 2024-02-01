package com.only.feign.anno;

import com.only.feign.core.RemoteCallBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RemoteCallBeanDefinitionRegistrar.class)
public @interface EnableRemoteCall {


    /**
     * 扫描的路径
     * @return
     */
    String[] scannerPackages() default {};


}
