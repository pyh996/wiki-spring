package com.wiki.spring.mapper;

import com.wiki.spring.domain.Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DocMapperCust {
    /* 自定义自增长的阅读数量*/
    public void increaseViewCount(@Param("id") Long id);
}
