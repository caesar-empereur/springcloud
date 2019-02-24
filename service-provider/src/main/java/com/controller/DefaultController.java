package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yang on 2018/1/6.
 */
@RestController
public class DefaultController extends BaseController {
    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @RequestMapping(value = "/service", method = RequestMethod.GET)
    public void service() {
        
        log.info("eureka-service: " + discoveryClient.getServices());
    }
    
}