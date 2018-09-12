package com.model;

import com.pojo.MomentPojo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
@Entity
public class Moment extends MomentPojo {
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Override
    public String getId() {
        return super.getId();
    }
    
    @Override
    public String getOwner() {
        return super.getOwner();
    }
    
    @Override
    public String getText() {
        return super.getText();
    }
    
    @Override
    public Date getCreateTime() {
        return super.getCreateTime();
    }
    
    @Override
    public String getImageList() {
        return super.getImageList();
    }
}
