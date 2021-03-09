package com.feign;

import com.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/11.
 */
@FeignClient(name = "service-provider")
@RequestMapping("/provider")
public interface ServiceProviderFeign {

    @GetMapping("/order/get")
    List<Order> getOrderList();
}
