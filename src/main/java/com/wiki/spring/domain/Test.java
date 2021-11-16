package com.wiki.spring.domain;


import lombok.Data;

/*
*
* No serializer found for class com.jeremxy.domain.EpgDetail and no properties
discovered to create BeanSerializer (to avoid exception, disable SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS)
*
* 需要带lombok
* */
@Data
public class Test {

    private Integer id;

    private String name;

    private String password;
}
