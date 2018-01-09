package com;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Created by yang on 2018/1/9.
 */
@SpringCloudApplication
@EnableZuulProxy
public class ZuulGatewayApplication {
    
    @Bean
    public ZuulAccessFilter zuulAccessFilter() {
        return new ZuulAccessFilter();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class, args);
    }
}
