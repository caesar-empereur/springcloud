package com;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by yang on 2018/1/9.
 */
@RestController
public class FeignController {
    
    private final Log log = LogFactory.getLog(this.getClass());
    
    @Resource
    private FeignService feignService;
    
    @RequestMapping(value = "/feign", method = RequestMethod.GET)
    public void feign() {
        feignService.feign();
    }
    
}
