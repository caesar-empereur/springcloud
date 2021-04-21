package com.controller;

import com.feign.ServiceProviderFeignB;
import com.model.Order;
import com.model.Storage;
import com.repository.OrderRepository;
import com.service.OrderServiceA;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
@RestController
@RequestMapping("/provider-a")
@Slf4j
public class ServiceProviderControllerA {

    @Resource
    private OrderServiceA orderServiceA;

    @ApiOperation(value = "获取order数据")
    @GetMapping("/order/list")
    public List<Order> list(){
        log.info("接收到请求");
        return orderServiceA.findAll();
    }

    @ApiOperation(value = "获取order数据")
    @GetMapping("/order/list/wait")
    public List<Order> message(){
        log.info("接收到请求, 开始延时");
        try {
            Thread.sleep(60000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("延时结束");
        return orderServiceA.findAll();
    }

    /**
     * 测试在接口超时时间的影响
     * @param order
     */
    @ApiOperation(value = "保存order数据")
    @PostMapping("/order/save/wait")
    public void saveOrderWait(@RequestBody Order order){
        log.info("-------------------service-provider-a 应用接收到请求");
        orderServiceA.saveOrderWait(order);
    }

    /**
     * 测试在链路调用中超时时间的影响
     * @param order
     */
    @ApiOperation(value = "保存order数据")
    @PostMapping("/order/save/call")
    public void saveOrderAndCall(@RequestBody Order order){
        log.info("-------------------service-provider-a 应用接收到请求");
        orderServiceA.saveOrderAndCall(order);
    }
}
