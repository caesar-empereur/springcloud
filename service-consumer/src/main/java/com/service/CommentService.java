package com.service;

import com.bridge.CommentBridge;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/11.
 */
@FeignClient(name = ProviderServiceName.SERVICE_NAME)
public interface CommentService extends CommentBridge {
}
