package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.pojo.FollowPojo;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
@Entity
public class Follow extends FollowPojo {
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Override
    public String getId() {
        return super.getId();
    }
    
    @Override
    public String getFollower() {
        return super.getFollower();
    }
    
    @Override
    public String getFollowing() {
        return super.getFollowing();
    }
    
    @Override
    public Date getCreateTime() {
        return super.getCreateTime();
    }
}
