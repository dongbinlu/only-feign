package com.only.feign.core;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.client.discovery.DiscoveryClient;

public abstract class AbstractDiscoveryClientSupport implements InitializingBean {

    /**
     * 依赖注入，使用属性注入方式注入的
     * 属性注入即通过setXXX()方法注入Bean的属性值或依赖对象
     */

    private DiscoveryClient discoveryClient;

    public DiscoveryClient getDiscoveryClient() {
        return discoveryClient;
    }

    public void setDiscoveryClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
