package com.wiki.spring.advice;

import com.wiki.spring.utils.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


/**
 * 全局异常捕获
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(value = Exception.class)   // 对所有的异常做捕获
    public CommonResponse<String> handlerCommerceException(HttpServletRequest req, Exception ex) {
        CommonResponse<String> response = new CommonResponse<>(-1, ex.getMessage());
        response.setData("");
        log.error("service  has error", ex.getMessage(), ex);
        return response;
    }
}


