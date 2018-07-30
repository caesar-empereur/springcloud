package com;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 注解被扫描都后会创建一个对应服务的 ribbon 客户端 因此可以配置
 */
@FeignClient(value = "eureka-service-provider", fallback = FeignServiceImpl.class)
public interface FeignService {
    
    /**
     * eureka-service-consumer模块里 是一个 ribbon 的 RestTemplate 负载均衡客户端
     * 在那里向服务提供者发起调用 feign 这个模块的作用就是简化 RestTemplate 的使用， 其实就是封装了API
     * EUREKA-SERVICE 是服务的名称, /service 是服务的地址 实现的结果是 和 ribbon 的客户端一样的
     */
    @RequestMapping(value = "/service", method = RequestMethod.GET)
    void feign();// 这里其实是发请求,跟mvc中请求入口不一样
    
    @RequestMapping(value = "/service-para", method = RequestMethod.GET)
    void feignWithPara(@RequestParam String name);// 带参数的请求
}
