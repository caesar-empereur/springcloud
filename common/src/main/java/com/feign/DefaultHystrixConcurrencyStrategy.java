package com.feign;

import com.ThreadLocalManagement;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/10.
 */
@Slf4j
public class DefaultHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        ThreadLocalManagement.setCurrentMsg("123");
        return new DefaultCallable<>(callable, ThreadLocalManagement.getInCurrentMsg());
    }

    static class DefaultCallable<T> implements Callable<T>{
        private Callable<T> callable;
        private Object value;

        public DefaultCallable(Callable<T> callable, Object value) {
            this.callable = callable;
            this.value = value;
        }

        @Override
        public T call() throws Exception {
//            if(this.value != null){
//                ThreadLocalManagement.setMsg(this.value);
//            }
            return this.callable.call();
        }
    }
}
