package com.controller;

import com.feign.ServiceProviderFeignA;
import com.feign.ServiceProviderFeignB;
import com.model.Order;
import com.serivce.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/11.
 */
@Slf4j
@RestController
@RequestMapping(value = "/consumer")
@Api(value = "评论接口")
public class ConsumerController {

    @Resource
    private ServiceProviderFeignA feignServiceA;

    @Resource
    private ServiceProviderFeignB feignB;

    @Resource
    private ConsumerService consumerService;

    @ApiOperation(value = "获取order数据A")
    @GetMapping("/order/list/a")
    public List<Order> listA(){
        return feignServiceA.getOrderList();
    }

    @ApiOperation(value = "获取order数据B")
    @GetMapping("/order/list/b")
    public List<Order> listB(){
        return feignB.getOrderList();
    }

    @ApiOperation(value = "获取order数据")
    @GetMapping("/order/list/wait")
    public List<Order> listWait(){
        return feignServiceA.getOrderListWait();
    }

    @ApiOperation(value = "保存order数据")
    @GetMapping("/order/save/wait")
    public void saveOrderWait(){
        Order order = new Order();
        order.setAge(20);
        order.setPhone("72634783267");
        order.setUsername("cwuriwy84383b283");
        feignServiceA.saveOrderWait(order);
    }


    @ApiOperation(value = "保存order数据")
    @GetMapping("/order/save/call")
    public void saveOrderAndCall(){
        log.info("consumer 服务接收到请求-------------------");
        consumerService.couponSave();
    }

    @ApiOperation(value = "保存order数据")
    @GetMapping("/order/save/get")
    public void testGet(Order order){
        log.info("consumer 服务接收到请求-------------------");
    }

}
