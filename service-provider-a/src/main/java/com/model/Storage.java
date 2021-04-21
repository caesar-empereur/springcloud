package com.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/27.
 */
@Data
public class Storage {

    private String id;

    private String orderId;

    private String skuName;

    private Integer pices;
}
