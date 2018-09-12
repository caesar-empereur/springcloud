package com.controller;

import com.bridge.CommentBridge;
import com.model.Comment;
import com.repository.CommentRepository;
import com.view.CommentParameter;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
@RestController
public class CommentProviderController implements CommentBridge {

    @Resource
    private CommentRepository commentRepository;

    @Override
    public Page<Comment> page(@RequestBody CommentParameter parameter) {
        return commentRepository.page(parameter);
    }

    @GetMapping("/insert")
    public void insert(){
        while (true){
            Comment comment = new Comment();
            comment.setCreateTime(new Date());
            comment.setContent("heheda");
            comment.setContentOwner(UUID.randomUUID().toString().replace("-",""));
            comment.setOwner(UUID.randomUUID().toString().replace("-",""));
            commentRepository.save(comment);
        }
    }

}
