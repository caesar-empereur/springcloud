package com.exception;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2018/9/10.
 */
public class BizException extends RuntimeException{

    public BizException(String message) {
        super(message);
    }
}
