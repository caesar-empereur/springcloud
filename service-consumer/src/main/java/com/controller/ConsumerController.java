package com.controller;

import com.model.Comment;
import com.service.FeignService;
import com.view.CommentParameter;
import com.view.CommentView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/11.
 */
@RestController
@RequestMapping(value = "/comment")
@Api(description = "评论接口")
public class ConsumerController {

    @Resource
    private FeignService feignService;

    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询")
    public Page<Comment> page(@ModelAttribute CommentParameter parameter) {
        return feignService.page(parameter);
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增评论")
    public void add(@ApiParam(value = "请求体") @RequestBody CommentView commentView){
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentView,comment);
//        feignService.save(comment);
    }
}
