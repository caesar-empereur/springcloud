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

    @Value("${dingding.chat-id}")
    private String chatId;

    @Value("${dingding.warning.text}")
    private String warningText;

    @Value("${dingding.appkey}")
    private String appKey;

    @Value("${dingding.send.gateway}")
    private String dingdingGateway;

    public String getDingdingGateway() {
        return dingdingGateway;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getConsulHost() {
        return consulHost;
    }

    public String getServices() {
        return services;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public Integer getRetryInterval() {
        return retryInterval;
    }

    public Integer getMonitorInterval() {
        return monitorInterval;
    }

    public String getChatId() {
        return chatId;
    }

    public String getWarningText() {
        return warningText;
    }
}
