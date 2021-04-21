package com.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/4/9.
 */
@Entity
@Table(name = "t_coupon")
@DynamicUpdate
@Data
public class Coupon {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    private String username;

    private String phone;

    private Integer age;
}
