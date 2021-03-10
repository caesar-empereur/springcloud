package com.feign;

import com.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/9.
 */
@Component
@Slf4j
public class ServiceProviderFallback implements ServiceProviderFeign{

    @Override
    public List<Order> getOrderList() {
        log.info("------------------feign 调用异常，进入熔断");
        return null;
    }
}
