package com.wiki.spring.advice;


import com.wiki.spring.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;



/**
 * 全局异常捕获
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {


    /* 自定义校验的异常处理*/
    @ExceptionHandler(value = org.springframework.validation.BindException.class)
    @ResponseBody
    public R validExceptionHandler(BindException e) {
        StringBuilder err_data = new StringBuilder();
        for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
            err_data.append(objectError.getDefaultMessage()).append(";");
        }
        log.info("err_data.toString() {}",err_data.toString());
        return R.error(10001, err_data.toString());
    }

    // 对所有的异常做捕获
    @ExceptionHandler(value = Exception.class)
    public R handlerCommerceException(HttpServletRequest req, Exception ex) {
        log.error("ex.getMessage(),{}",ex.getMessage());
        return R.error(-1, ex.getMessage());

    }
}


