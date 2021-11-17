//package com.wiki.spring.advice;
//
//
//
//
//import com.wiki.spring.annotation.IgnoreResponseAdvice;
//import com.wiki.spring.utils.CommonResponse;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
///**
// * <h1>实现统一响应,    @IgnoreResponseAdvice 只有打这个tag才算是不统一响应</h1>
// */
//@RestControllerAdvice(value = "com.wiki.spring")
//@SuppressWarnings("all")
//public class CommResponseDataAdvice implements ResponseBodyAdvice<Object> {
//    /**
//     * <h1>判断是否需要对响应进行处理</h1>
//     */
//    @Override
//    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
//        // 获取controller的类.如果被IgnoreResponseAdvice注解标识,就不需要处理
//        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
//            return false;
//        }
//        // 获取controller的方法.如果被IgnoreResponseAdvice注解标识,就不需要处理
//        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
//            return false;
//        }
//        return true;  // 需要去包装
//    }
//
//    @SuppressWarnings("all")
//    @Override
//    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
//                                  MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
//                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        // 定义最终的返回对象
//        CommonResponse<Object> response = new CommonResponse<>(0, "");
//        if (null == o) {
//            return response;
//        } else if (o instanceof CommonResponse) { // 如果o对象和CommonResponse是同一个类型
//            response = (CommonResponse<Object>) o;  // 强制转换
//        } else {
//            response.setData(o);
//        }
//        return response;
//    }
//}
