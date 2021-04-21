package com.feign;

import com.model.Order;
import com.model.Storage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/11.
 */
@FeignClient(name = "service-provider-b")
public interface ServiceProviderFeignB {

    @GetMapping("/provider-b/order/get")
    List<Order> getOrderList();

    @PostMapping("/provider-b/order/save")
    void save(@RequestBody Order order);

    @PostMapping("/provider-b/storage/save/wait")
    void storageSave(@RequestBody Storage storage);
}
