package com.nextclassai.logindemo.common;

/**
 * @author Ststorytony
 * @date 2019/6/14 17:01
 * Description:  返回状态封装
 */
public class WrapMapper {
    private WrapMapper() {
    }

    public static  <T> Wrapper<T> wrap(int code, String msg, T result) {
        return new Wrapper<>(code, msg, result);
    }

    public static <T> Wrapper<T> wrap(int code, String msg) {
        return new Wrapper<>(code, msg);
    }

    public static <T> Wrapper<T> ok(T result) {
        return new Wrapper<>(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, result);
    }

    public static Wrapper error() {
        return new Wrapper(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
    }

    public static Wrapper error(String errormsg){
        return new Wrapper(Wrapper.ERROR_CODE,errormsg);
    }
}
