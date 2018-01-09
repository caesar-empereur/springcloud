package com;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yang on 2018/1/9.
 */
@FeignClient("EUREKA-SERVICE")
public interface FeignService {
    
    @RequestMapping(value = "")
    void ribbon();
}
