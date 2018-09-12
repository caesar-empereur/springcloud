package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/5/24.
 */
@Component
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class MonitorConfig {

    @Value("${spring.cloud.consul.host}")
    private String consulHost;

    @Value("${monitor.service}")
    private String services;

    @Value("${monitor.retry-times}")
    private Integer retryTimes;

    @Value("${monitor.retryInterval}")
    private Integer retryInterval;

    @Value("${monitor.monitorInterval}")
    private Integer monitorInterval;
}
