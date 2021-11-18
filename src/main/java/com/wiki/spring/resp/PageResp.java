package com.wiki.spring.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
public class PageResp<T> {
    private long total;

    private List<T> list;


}
