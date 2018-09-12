package com.view;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
@ApiModel
public class MomentParameter extends PageParameter {

    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
