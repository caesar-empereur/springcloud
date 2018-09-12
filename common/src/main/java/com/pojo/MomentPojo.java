package com.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
public class MomentPojo extends BasePojo {

    private String owner;

    private String text;

    private String imageList;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageList() {
        return imageList;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList;
    }
}
