package com.nextclassai.logindemo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Ststorytony
 * @date 2019/6/14 16:55
 * Description:  返回状态包装类
 */
@Data
public class Wrapper<T> implements Serializable {
    private static final long serialVersionUID = 8247569904881340463L;

    public static final int SUCCESS_CODE = 200;

    public static final String SUCCESS_MESSAGE = "操作成功";

    public static final int ERROR_CODE = 500;

    public static final String ERROR_MESSAGE = "操作失败";

    private int code;

    private String msg;

    private T result;

    public Wrapper(){
        this(SUCCESS_CODE,SUCCESS_MESSAGE);
    }

    public Wrapper(int code, String message) {
        this(code, message, null);
    }

    public Wrapper(int code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    @JsonIgnore
    public boolean success() {
        return Wrapper.SUCCESS_CODE == this.code;
    }

    @JsonIgnore
    public boolean error() {
        return !success();
    }
}
