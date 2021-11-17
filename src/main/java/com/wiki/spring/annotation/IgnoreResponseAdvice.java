package com.wiki.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解,修饰语句
 * <h1>忽略统一响应注解定义</h1>
 */
@Target({ElementType.TYPE, ElementType.METHOD})  // 可以在类和方法上表示
@Retention(RetentionPolicy.RUNTIME)   // 执行到什么时候
public @interface IgnoreResponseAdvice {

}
