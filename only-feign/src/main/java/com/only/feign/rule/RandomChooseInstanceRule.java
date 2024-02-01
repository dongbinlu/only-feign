package com.only.feign.rule;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
import java.util.Random;

public class RandomChooseInstanceRule implements IChooseInstanceRules{
    @Override
    public ServiceInstance chooseInstance(List<ServiceInstance> serviceInstanceList) {
        if (CollectionUtils.isNotEmpty(serviceInstanceList)){
            int index = new Random().nextInt(serviceInstanceList.size());
            return serviceInstanceList.get(index);
        }
        return null;
    }
}
