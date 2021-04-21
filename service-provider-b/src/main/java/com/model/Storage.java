package com.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/27.
 */
@Entity
@Table(name = "t_storage")
@Data
public class Storage {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    private String orderId;

    private String skuName;

    private Integer pices;
}
