package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by yang on 2018/1/6.
 */
@EnableAutoConfiguration
@ComponentScan("com")
@EntityScan("com")
@EnableEurekaServer
@SpringBootApplication
public class EurekaOneApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(EurekaOneApplication.class, args);
    }
}
