package com.spring;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/14.
 */
//@Component
@Slf4j
public class GlobalExceptionHandler implements HandlerExceptionResolver, Ordered {
    
    private static final String ERROR_MESSAGE = "服务器挂掉了";
    
    @Override
    public int getOrder() {
        return 0;
    }
    
    @ResponseBody
    @Nullable
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @Nullable Object handler,
                                         Exception ex) {
        log.error("进程pid: " + ManagementFactory.getRuntimeMXBean().getName());
        log.error("线程: " + Thread.currentThread().getName());
        
        ModelAndView modelAndView = new ModelAndView();
        MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
        Map<String, Object> attributes = new HashMap<>(2);
        attributes.put("succeed", false);
        
        String errorMessage = null;
        
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException validException = (MethodArgumentNotValidException) ex;
            FieldError fieldError = validException.getBindingResult().getFieldErrors().get(0);
            errorMessage = fieldError.getField() + " " + fieldError.getDefaultMessage();
        }
        else if (ex instanceof HystrixRuntimeException) {
            Throwable throwable = ex.getCause();
            if (throwable instanceof FeignException) {
                String content = throwable.getMessage();
                JsonObject jsonpObject = new JsonParser().parse("{" + content + "}")
                                                         .getAsJsonObject();
                errorMessage = jsonpObject.get("message").getAsString();
            }
        }
        else {
            errorMessage = ERROR_MESSAGE;
        }
        attributes.put("message", errorMessage);
        mappingJackson2JsonView.setAttributesMap(attributes);
        modelAndView.setView(mappingJackson2JsonView);
        return modelAndView;
    }
}
