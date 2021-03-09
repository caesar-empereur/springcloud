package com.repository;

import com.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/1/21.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
