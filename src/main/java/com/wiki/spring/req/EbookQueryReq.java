package com.wiki.spring.req;


import lombok.Data;
import lombok.Getter;


@Data
public class EbookQueryReq {
    private Long id;

    private String name;


//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

}
