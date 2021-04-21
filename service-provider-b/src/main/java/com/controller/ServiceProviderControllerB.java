package com.controller;

import com.model.Order;
import com.model.Storage;
import com.repository.OrderRepository;
import com.service.OrderServiceB;
import com.service.StorageService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
@RestController
@RequestMapping("/provider-b")
@Slf4j
public class ServiceProviderControllerB {

    @Resource
    private OrderServiceB orderServiceB;

    @Resource
    private StorageService storageService;

    @ApiOperation(value = "获取order数据")
    @GetMapping("/order/get")
    public List<Order> message(){
        log.info("接收到请求");
        return orderServiceB.findAll();
    }

    @ApiOperation(value = "保存order数据")
    @PostMapping("/order/save/wait")
    public void orderSave(@RequestBody Order order){
        orderServiceB.save(order);
    }

    @ApiOperation(value = "保存storage数据")
    @PostMapping("/storage/save/wait")
    public void storageSave(@RequestBody Storage storage){
        log.info("-------------------service-provider-b 应用接收到请求");
        storageService.save(storage);
    }
    
}
