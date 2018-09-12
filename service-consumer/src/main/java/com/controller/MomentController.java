package com.controller;

import com.model.Moment;
import com.service.MomentService;
import com.view.MomentParameter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/11.
 */
@RestController
@RequestMapping(value = "/moment")
@Api(value = "朋友圈接口")
public class MomentController {

    @Resource
    private MomentService momentService;

    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询")
    public Page<Moment> page(MomentParameter parameter) {
        return momentService.page(parameter);
    }
}
