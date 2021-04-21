package com.service;

import com.feign.ServiceProviderFeignB;
import com.model.Order;
import com.model.Storage;
import com.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/27.
 */
@Service
public class OrderServiceA {

    @Resource
    private ServiceProviderFeignB feignB;

    @Resource
    private OrderRepository orderRepository;

    /**
     * 测试接口的实际超时时间与配置的接口请求超时时间的影响
     * @param order
     */
    @Transactional
    public void saveOrderWait(Order order){
        orderRepository.save(order);

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存订单数据并且发出远程调用
     * @param order
     */
    @Transactional(timeout = 5)
    public void saveOrderAndCall(Order order){
        orderRepository.save(order);

        Storage storage = new Storage();
        storage.setSkuName("sldfjsi");
        storage.setOrderId(UUID.randomUUID().toString().replace("-", ""));
        storage.setPices(10);
        //调用B 服务保存 storage 信息
        feignB.storageSave(storage);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }
}
