package com.only.feign.controller;

import com.only.feign.entity.ProductInfo;
import com.only.feign.remote.ProductRemoteCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private ProductRemoteCall productRemoteCall;

    @RequestMapping("/remote/call")
    public ProductInfo test() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName("华为p40");
        productInfo.setProductNo("1");
        productInfo.setProductPrice(5888);
        productInfo.setProductStore("99");
        return productRemoteCall.qryProductInfo3(productInfo);
    }

    @RequestMapping(value = "/product/qryProductInfo2", method = {RequestMethod.POST})
    ProductInfo qryProductInfo3(@RequestBody ProductInfo productInfo) {
        return productInfo;
    }
}