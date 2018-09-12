package com.bridge;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Comment;
import com.view.CommentParameter;

import java.util.List;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
@RequestMapping(value = "/comment")
public interface CommentBridge {
    
    @PostMapping(value = "/page")
    Page<Comment> page(@RequestBody CommentParameter parameter);
}
