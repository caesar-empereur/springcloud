package com;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
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
    @HystrixCommand(fallbackMethod = "fallback", observableExecutionMode = ObservableExecutionMode.EAGER, ignoreExceptions = RuntimeException.class)
    public void service() {
        log.info(restTemplate.getForEntity("http://EUREKA-SERVICE/service",
                                           String.class));
//        throw new RuntimeException("execution exception");
    }
    
    // 这里是实现服务降级的方法,就是服务调用失败时的备用处理
    private void fallback(Throwable e) {
        log.info("hystix callback ");
        //这里是调用出现异常时 拿到具体的异常类型
        e.printStackTrace();
    }
    
}
