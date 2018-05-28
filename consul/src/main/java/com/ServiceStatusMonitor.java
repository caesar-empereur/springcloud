package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.app.exception.BusinessException;
import com.app.util.JsonUtils;

/**
 * Created by Administrator on 2018/5/24.
 */
@Component
public class ServiceStatusMonitor implements InitializingBean {
    
    private static final Log log = LogFactory.getLog(ServiceStatusMonitor.class);
    
    private static final String MAP_CHECK_KEY = "Checks";
    
    private static final String MAP_SERVICE_NAME_KEY = "ServiceName";
    
    private static final String MAP_NODE_KEY = "Node";
    
    private static final String MAP_DATA_CENTER_KEY = "Datacenter";
    
    private static final String EXCEPTION_TEXT = "请求 consul 结果解析异常";
    
    @Resource
    private MonitorConfig monitorConfig;
    
    private static final RestTemplate restTemplate = new RestTemplate();
    
    @Override
    public void afterPropertiesSet() throws Exception {
        final String[] services = monitorConfig.getServices().split(",");
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(services.length + 2);

        for (int i = 0; i < services.length; i++) {
            final int[] j = { 0 };
            int index = j[0] + i;
            executorService.scheduleAtFixedRate(() -> {
                try {
                    checkServiceStatus(services[index]);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }, 0, monitorConfig.getMonitorInterval(), TimeUnit.SECONDS);
        }
    }

    //检查服务状态是否正常并发送钉钉消息
    private void checkServiceStatus(String serviceName) {
        int times = monitorConfig.getRetryTimes();
        
        ServiceStatusResponse statusResponse = getStatusResponse(serviceName);
        
        while (times > 0 && !statusResponse.isAlive()) {
            try {
                TimeUnit.SECONDS.sleep(monitorConfig.getRetryInterval());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            statusResponse = getStatusResponse(serviceName);
            times--;
        }
        if (statusResponse.isAlive()) {
            return;
        }
        sendWarningMessage(statusResponse.getServiceName(), statusResponse.getDataCenter());
    }
    
    // 发钉钉消息
    private void sendWarningMessage(String serviceName, String datacenter) {
        Map<String, String> body = new HashMap<>(4);
        body.put("chatId", monitorConfig.getChatId());
        body.put("msgType", "text");
        body.put("content", String.format("数据中心: %s", datacenter)
                            + String.format(", 服务名: %s", serviceName)
                            + monitorConfig.getWarningText());
        Map<String, Object> parameter = new HashMap<>(4);
        parameter.put("method", "dingtalk.group.message.send");
        parameter.put("appKey", monitorConfig.getAppKey());
        parameter.put("body", JsonUtils.toJson(body));
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>>
                httpEntity = new HttpEntity<>(parameter, headers);
        ResponseEntity<String>
                responseEntity = restTemplate.postForEntity(monitorConfig.getDingdingGateway(),
                                                            httpEntity,
                                                            String.class);
        Map dingdingResponse = JsonUtils.parseMap(responseEntity.getBody());
        if ("success".equals(dingdingResponse.get("flag").toString())) {
            log.info("发送钉钉消息成功, " + String.format("数据中心: %s", datacenter)
                     + String.format(", 服务名: %s", serviceName));
        }
        else {
            log.info("发送钉钉消息失败, " + String.format("数据中心: %s", datacenter)
                     + String.format(", 服务名: %s", serviceName));
        }
    }

    //获取服务状态结果
    private ServiceStatusResponse getStatusResponse(String serviceName) {
        log.info("获取服务状态: " + serviceName);
        
        String url = monitorConfig.getConsulHost() + "/v1/health/service/";
        url += serviceName + "?dc=dc1&token=";
        ResponseEntity<List>
                responseEntity = restTemplate.getForEntity(url, List.class);
        Map map = null;
        try {
            map = JsonUtils.parseMap(JsonUtils.toJson(responseEntity.getBody()
                                                                    .get(0)));
        }
        catch (Exception e) {
            log.error("配置文件的服务名出错：" + serviceName, e);
            throw new RuntimeException("配置文件的服务名出错：" + serviceName);
        }
        int index = 0;
        String name = getValueFromMap(map, MAP_CHECK_KEY, MAP_SERVICE_NAME_KEY, index);
        if (StringUtils.isBlank(name)) {
            name = getValueFromMap(map, MAP_CHECK_KEY, MAP_SERVICE_NAME_KEY, ++index);
        }
        if (StringUtils.isBlank(name)) {
            log.error(EXCEPTION_TEXT);
            throw new RuntimeException(EXCEPTION_TEXT);
        }
        String status = getValueFromMap(map, MAP_CHECK_KEY, "Status", index);
        String datacenter = getValueFromMap(map, MAP_NODE_KEY, MAP_DATA_CENTER_KEY, 0);
        return new ServiceStatusResponse(serviceName, datacenter, "passing".equals(status));
    }

    //解析服务状态结果
    private String getValueFromMap(Map map, String key, String subkey, Integer index) {
        if (map == null || map.isEmpty()) {
            throw new BusinessException("");
        }
        String value = null;
        try {
            if (MAP_NODE_KEY.equals(key)) {
                value = ((Map) map.get(key)).get(subkey).toString();
            }
            else {
                value = ((Map) ((ArrayList) map.get(key)).get(index)).get(subkey).toString();
            }
        }
        catch (Exception e) {
            log.error(EXCEPTION_TEXT, e);
            throw new BusinessException(EXCEPTION_TEXT);
        }
        if (!subkey.equals("ServiceName") && StringUtils.isBlank(value)) {
            log.error(EXCEPTION_TEXT);
            throw new BusinessException(EXCEPTION_TEXT);
        }
        return value;
    }
    
    static class ServiceStatusResponse {
        
        private String serviceName;
        
        private String dataCenter;
        
        private boolean alive;
        
        public String getServiceName() {
            return serviceName;
        }
        
        public String getDataCenter() {
            return dataCenter;
        }
        
        public boolean isAlive() {
            return alive;
        }
        
        public ServiceStatusResponse(String serviceName,
                                     String dataCenter,
                                     boolean alive) {
            this.serviceName = serviceName;
            this.dataCenter = dataCenter;
            this.alive = alive;
        }
    }
}
