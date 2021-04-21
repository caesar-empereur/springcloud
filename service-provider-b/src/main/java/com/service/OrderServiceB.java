package com.service;

import com.model.Order;
import com.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/27.
 */
@Service
@Slf4j
public class OrderServiceB {

    @Resource
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    @Transactional
    public void save(Order order){
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        orderRepository.save(order);
        log.info("---------------------- order 数据插入成功");
    }
}
