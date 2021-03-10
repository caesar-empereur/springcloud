package com.controller;

import com.model.Order;
import com.repository.OrderRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
@RestController
@RequestMapping("/provider")
@Slf4j
public class ServiceProviderController {

    @Resource
    private OrderRepository orderRepository;

    @ApiOperation(value = "获取order数据")
    @GetMapping("/order/get")
    public List<Order> message(){
        log.info("接收到请求");
//        throw new RuntimeException("peovider 抛异常");
        return orderRepository.findAll();
    }
    
}
