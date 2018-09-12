package com.service;

import com.bridge.MomentBridge;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/11.
 */
@FeignClient(name = ProviderServiceName.SERVICE_NAME)
public interface MomentService extends MomentBridge {
}
