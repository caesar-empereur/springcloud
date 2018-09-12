package com.bridge;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Moment;
import com.view.MomentParameter;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
@RequestMapping(value = "/moment")
public interface MomentBridge {

    @PostMapping(value = "/page")
    Page<Moment> page(@RequestBody MomentParameter parameter);
}
