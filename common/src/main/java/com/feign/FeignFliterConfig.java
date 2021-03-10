package com.feign;

import com.ThreadLocalManagement;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/9.
 */
@Configuration
@Slf4j
public class FeignFliterConfig {

    @Bean
    public RequestInterceptor headerInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                log.info("--------------------feign 调用前拦截，当前线程 " + Thread.currentThread().getName());
                Object value = ThreadLocalManagement.getInCurrentMsg();
                if (value != null){
                    log.info("--------------------feign 调用前拦截到 traceId " + value);
                    template.header("traceId", (String) value);
                }
            }
        };
    }
}
