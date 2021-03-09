package com.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/1/21.
 */
@Data
public class Order implements Serializable {

    private String id;

    private String username;

    private String phone;

    private Integer age;

}
