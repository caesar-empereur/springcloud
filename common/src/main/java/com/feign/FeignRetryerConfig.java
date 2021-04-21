package com.feign;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/15.
 */
@Configuration
public class FeignRetryerConfig {

    @Bean
    public Retryer retryer(){
        Retryer retryer = new Retryer.Default(10, 10, 3);
        return retryer;
    }
}
