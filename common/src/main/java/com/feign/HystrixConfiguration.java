package com.feign;

import com.ThreadLocalManagement;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.Callable;

/**
 * @Description feign-hystrix 线程并发的策略封装类，并不会修改并发模式，还是默认的线程隔离模式
 * 并发模式包括线程隔离模式与信号量隔离模式
 * @author: yangyingyang
 * @date: 2021/3/10.
 */
//@Configuration
public class HystrixConfiguration {

//    @PostConstruct
    public void init() {
        HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins.getInstance().getCommandExecutionHook();
        HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance().getEventNotifier();
        HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance().getMetricsPublisher();
        HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance().getPropertiesStrategy();

        HystrixPlugins.reset();
        //必须先重置之前的配置，重置之后这些配置都要重新设置进去
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new DefaultHystrixConcurrencyStrategy());

        HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
        HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
        HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
        HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
    }

    /**
     * 这里的并发策略封装逻辑不会修改并发模式，还是默认的线程隔离的模式
     * 只是将当前线程拿到的变量通过实例变量传递到新的线程类里面
     */
    class DefaultHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

        @Override
        public <T> Callable<T> wrapCallable(Callable<T> callable) {
//            ThreadLocalManagement.setCurrentMsg("123");
            return new com.feign.HystrixConfiguration.DefaultCallable<>(callable, ThreadLocalManagement.getInCurrentMsg());
        }

    }

    class DefaultCallable<T> implements Callable<T>{
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
