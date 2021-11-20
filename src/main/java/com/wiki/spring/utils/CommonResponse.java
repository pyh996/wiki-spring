package com.wiki.spring.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * {
 * code:0,
 * message:"",
 * data:{}
 * }
 */
@Data
@NoArgsConstructor   // 无参的构造方式
@AllArgsConstructor   // 全参的构造方式
public class CommonResponse<T> implements Serializable {
    private Integer code;
    private String msg;
    private T Data;

    public CommonResponse(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }


}
