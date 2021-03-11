package com;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/9.
 */
public class ThreadLocalManagement {

    private static final ThreadLocal<Object> INHERIT_CURRENT_MSG = new InheritableThreadLocal<>();

    public static void setInCurrentMsg(Object value){
        INHERIT_CURRENT_MSG.set(value);
    }

    public static Object getInCurrentMsg(){
        return INHERIT_CURRENT_MSG.get();
    }

}
