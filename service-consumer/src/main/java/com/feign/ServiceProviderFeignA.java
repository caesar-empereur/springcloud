package com.feign;

import com.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/11.
 */
@FeignClient(name = "service-provider-a")
public interface ServiceProviderFeignA {

    @GetMapping("/provider-a/order/list")
    List<Order> getOrderList();

    @GetMapping("/provider-a/order/list/wait")
    List<Order> getOrderListWait();

    @PostMapping("/provider-a/order/save/wait")
    void saveOrderWait(@RequestBody Order order);

    @PostMapping("/provider-a/order/save/call")
    void saveOrderAndCall(@RequestBody Order order);
}
