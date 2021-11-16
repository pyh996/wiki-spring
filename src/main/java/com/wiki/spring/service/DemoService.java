package com.wiki.spring.service;


import com.wiki.spring.domain.Demo;
import com.wiki.spring.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    @Autowired
    private DemoMapper demoMapper;

    public List<Demo> getSelect() {
        return demoMapper.selectByExample(null);
    }
}
