package com.repository;

import com.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/4/9.
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon, String> {
}
