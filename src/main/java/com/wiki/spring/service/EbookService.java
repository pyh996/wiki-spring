package com.wiki.spring.service;


import com.github.pagehelper.PageHelper;
import com.wiki.spring.domain.Demo;
import com.wiki.spring.domain.Ebook;
import com.wiki.spring.domain.EbookExample;
import com.wiki.spring.mapper.DemoMapper;
import com.wiki.spring.mapper.EbookMapper;
import com.wiki.spring.req.EbookQueryReq;

import com.wiki.spring.resp.EbookQueryResp;
import com.wiki.spring.utils.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class EbookService {

    @Autowired(required = false)
    private EbookMapper ebookMapper;

    public List<EbookQueryResp> list(EbookQueryReq req) {
        PageHelper.startPage(1, 3);
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        List<Ebook> ebooksList = ebookMapper.selectByExample(ebookExample);
        // 列表赋值
        return CopyUtil.copyList(ebooksList, EbookQueryResp.class);
    }
}
