package com.wiki.spring.req;

import javax.validation.constraints.NotNull;

public class EbookQueryReq  extends PageReq {

    private String id;


    private String name;


    public String getCategoryId2() {
        return categoryId2;
    }

    public void setCategoryId2(String categoryId2) {
        this.categoryId2 = categoryId2;
    }

    private String categoryId2;

    @Override
    public String toString() {
        return "EbookQueryReq{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", categoryId2='" + categoryId2 + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
