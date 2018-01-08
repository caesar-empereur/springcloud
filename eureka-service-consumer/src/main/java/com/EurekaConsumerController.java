package com;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by yang on 2018/1/8.
 */
@RestController
public class EurekaConsumerController {
    
    private final Log log = LogFactory.getLog(this.getClass());
    
    @Resource
    private RestTemplate restTemplate;
    
    @RequestMapping(value = "/consumer", method = RequestMethod.GET)
    public void service() {
        log.info(restTemplate.getForEntity("http://EUREKA-SERVICE/service", String.class));
    }
}
