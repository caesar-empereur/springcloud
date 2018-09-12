package com.pojo;

import lombok.Data;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
public class FollowPojo extends BasePojo {

    private String follower;

    private String following;

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }
}
