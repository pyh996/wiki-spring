package com.wiki.spring.advice;

import com.wiki.spring.utils.CommonResponse;
import com.wiki.spring.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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

    @ExceptionHandler(value = Exception.class)   // 对所有的异常做捕获
    public CommonResponse<String> handlerCommerceException(HttpServletRequest req, Exception ex) {
        // e.getBindingResult().getAllErrors().get(0).getDefaultMessage()


        CommonResponse<String> response = new CommonResponse<>(-1, ex.getMessage());
        response.setData("");
        log.error("service  has error", ex.getMessage(), ex);
        return response;
    }
}


