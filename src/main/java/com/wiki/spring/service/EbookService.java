package com.wiki.spring.service;


import com.wiki.spring.domain.Demo;
import com.wiki.spring.domain.Ebook;
import com.wiki.spring.mapper.DemoMapper;
import com.wiki.spring.mapper.EbookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbookService {

    @Autowired
    private EbookMapper ebookMapper;

    public List<Ebook> getSelect() {
        return ebookMapper.selectByExample(null);
    }
}
