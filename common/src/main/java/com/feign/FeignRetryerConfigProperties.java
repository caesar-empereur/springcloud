package com.feign;

import feign.Retryer;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/22.
 */
public class FeignRetryerConfigProperties extends Retryer.Default{

    public FeignRetryerConfigProperties() {
        super(10,10,3);
    }
}
