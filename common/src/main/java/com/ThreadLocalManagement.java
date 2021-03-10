package com;

/**
 * @Description
 * @author: yangyingyang
 * @date: 2021/3/9.
 */
public class ThreadLocalManagement {

    private static final ThreadLocal<Object> INHERIT_CURRENT_MSG = new InheritableThreadLocal<>();

    private static final ThreadLocal<Object> CURRENT_MSG = new ThreadLocal<>();

    public static void setInCurrentMsg(Object value){
        INHERIT_CURRENT_MSG.set(value);
    }

    public static Object getInCurrentMsg(){
        return INHERIT_CURRENT_MSG.get();
    }

    public static void setCurrentMsg(Object value){
        CURRENT_MSG.set(value);
    }

    public static Object getCurrentMsg(){
        return CURRENT_MSG.get();
    }
}
