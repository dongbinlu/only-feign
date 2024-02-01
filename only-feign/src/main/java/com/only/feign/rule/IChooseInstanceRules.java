package com.only.feign.rule;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface IChooseInstanceRules {

    ServiceInstance chooseInstance(List<ServiceInstance> serviceInstanceList);

}
