package com.wiki.spring.mapper;

import com.wiki.spring.domain.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;





public interface TestMapper {

    public List<Test> list();
}
