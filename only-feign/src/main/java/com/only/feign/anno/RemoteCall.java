package com.only.feign.anno;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface RemoteCall {

    String serviceName() default "";

    String path() default "";

}
