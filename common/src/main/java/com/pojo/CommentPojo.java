package com.pojo;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
public class CommentPojo extends BasePojo {

    private String contentOwner;

    private String owner;

    private String content;

    public String getContentOwner() {
        return contentOwner;
    }

    public void setContentOwner(String contentOwner) {
        this.contentOwner = contentOwner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
