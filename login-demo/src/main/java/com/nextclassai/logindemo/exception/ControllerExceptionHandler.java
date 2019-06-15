package com.nextclassai.logindemo.exception;

import com.nextclassai.logindemo.common.WrapMapper;
import com.nextclassai.logindemo.common.Wrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Ststorytony
 * @date 2019/6/14 17:08
 * Description: 异常统一处理
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public Wrapper illegalException(IllegalArgumentException e){
        return WrapMapper.error("参数有误");
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Wrapper exception(Exception e){
        return WrapMapper.error(e.getMessage());
    }
}
