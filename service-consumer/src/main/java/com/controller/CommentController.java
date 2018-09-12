package com.controller;

import com.model.Comment;
import com.service.CommentService;
import com.view.CommentParameter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/11.
 */
@RestController
@RequestMapping(value = "/comment")
@Api(description = "评论接口")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询")
    public Page<Comment> page(@ModelAttribute CommentParameter parameter) {
        return commentService.page(parameter);
    }


}
