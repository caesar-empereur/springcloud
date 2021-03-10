package com.spring;

import com.ThreadLocalManagement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/9.
 */
@Configuration
@Slf4j
public class SpringbootFilterConfig {

    private static final String TRACE_ID = "traceId";

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SpringbootRequestFilter());
        registration.addUrlPatterns("/consumer/*", "/provider/*");
        registration.setName("RequestFilter");
        registration.setOrder(1);
        return registration;
    }

    class SpringbootRequestFilter implements Filter {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
                                                          IOException,
                                                          ServletException {
            log.info("---------------------springboot 拦截器拦截，当前线程 " + Thread.currentThread().getName());
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String value = httpServletRequest.getHeader(TRACE_ID);
            if (value != null) {
                log.info("---------------------springboot 拦截器拦截到 traceId " + value);
                ThreadLocalManagement.setInCurrentMsg(value);
            }
            chain.doFilter(request, response);
        }
    }
}
