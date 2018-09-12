package com.controller;

import com.bridge.MomentBridge;
import com.model.Moment;
import com.repository.MomentRepository;
import com.view.MomentParameter;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
@RestController
public class MomentProviderController implements MomentBridge {

    @Resource
    private MomentRepository momentRepository;

    @Override
    public Page<Moment> page(MomentParameter parameter) {
        return momentRepository.page(parameter);
    }
}
