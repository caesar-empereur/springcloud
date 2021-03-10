package com.controller;

import com.feign.ServiceProviderFeign;
import com.model.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/11.
 */
@RestController
@RequestMapping(value = "/consumer")
@Api(value = "评论接口")
public class ConsumerController {

    @Resource
    private ServiceProviderFeign feignService;

    @ApiOperation(value = "获取order数据")
    @GetMapping("/order/get")
    public List<Order> message(){
        return feignService.getOrderList();
    }
}
