package com.model;

import com.pojo.CommentPojo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
@Entity
public class Comment extends CommentPojo {
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Override
    public String getId() {
        return super.getId();
    }
    
    @Override
    public String getContentOwner() {
        return super.getContentOwner();
    }
    
    @Override
    public String getOwner() {
        return super.getOwner();
    }
    
    @Override
    public String getContent() {
        return super.getContent();
    }
    
    @Override
    public Date getCreateTime() {
        return super.getCreateTime();
    }
}
