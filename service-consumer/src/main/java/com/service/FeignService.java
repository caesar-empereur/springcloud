package com.service;

import com.model.Comment;
import com.view.CommentParameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/11.
 */
@FeignClient(name = "feign-service")
@RequestMapping(value = "/feign-service")
public interface FeignService {

    @PostMapping(value = "/page")
    Page<Comment> page(@RequestBody CommentParameter parameter);

    @PostMapping(value = "/save")
    void save(@RequestBody Comment comment);
}
