package com;

/**
 * feign调用出现异常时可以指定服务降级的实现类
 * 指定降级后的处理
 */
public class FeignServiceImpl implements FeignService {
    
    @Override
    public void feign() {
        //do
    }
    
    @Override
    public void feignWithPara(String name) {
        //do
    }
}
