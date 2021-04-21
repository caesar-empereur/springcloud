package com.serivce;

import com.feign.ServiceProviderFeignA;
import com.model.Coupon;
import com.model.Order;
import com.repository.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/4/8.
 */
@Service
public class ConsumerService {

    @Resource
    private CouponRepository couponRepository;

    @Resource
    private ServiceProviderFeignA providerFeignA;

    @Transactional(timeout = 7)
    public void couponSave(){
        Coupon coupon = new Coupon();
        coupon.setAge(456);
        coupon.setUsername("dtmyuruy68my");
        coupon.setPhone("5897578657858");
        couponRepository.save(coupon);

        Order order = new Order();
        order.setAge(345);
        order.setUsername("botyiunt6");
        order.setPhone("0689548684569");
        providerFeignA.saveOrderAndCall(order);
    }
}
