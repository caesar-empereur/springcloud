package com.service;

import java.lang.management.ManagementFactory;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.exception.BizException;
import com.model.Comment;
import com.view.CommentParameter;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/13.
 */
@Component
@Slf4j
public class CommentServiceFallbackFactory implements
                                           feign.hystrix.FallbackFactory<CommentService> {
    
    @Override
    public CommentService create(Throwable cause) {
        final String message = cause.getMessage();
        return new CommentService() {
            @Override
            public Page<Comment> page(CommentParameter parameter) {
                return null;
            }
            
            @Override
            public void save(Comment comment) {
                log.error(ManagementFactory.getRuntimeMXBean().getName());
                log.error(Thread.currentThread().getName());
                throw new BizException(message);
            }
        };
    }
}
