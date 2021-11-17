package com.wiki.spring.utils;


import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;


public class R extends HashMap<String, Object> {

    public R() {
        put("code", 200);
        put("msg", "success");
        put("data", "");
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /* 默认返回200*/
    public static R ok() {
        return new R();
    }

    public static R ok(String msg, Object data) {
        R r = new R();
        r.put("msg", msg);
        r.put("data", data);
        return r;
    }
    // OK 只用传递data
    public static R ok(Object data) {
        R r = new R();
        r.put("msg", "ok");
        r.put("data", data);
        return r;
    }

    // 失败 只用传递message
    public static R error(String msg) {
        return error(10001, msg);
    }

    /* 失败默认返回10001*/
    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        r.put("data", "");
        return r;
    }
    // 失败 只用传递code,message
    public static R error() {
        return error(10001, "未知异常，请联系管理员");
    }

}
