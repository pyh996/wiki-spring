package com.wiki.spring.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wiki.spring.domain.Demo;
import com.wiki.spring.domain.Ebook;
import com.wiki.spring.domain.EbookExample;
import com.wiki.spring.mapper.DemoMapper;
import com.wiki.spring.mapper.EbookMapper;
import com.wiki.spring.req.EbookQueryReq;

import com.wiki.spring.req.EbookSaveReq;
import com.wiki.spring.resp.EbookQueryResp;
import com.wiki.spring.resp.PageResp;
import com.wiki.spring.utils.CopyUtil;
import com.wiki.spring.utils.NowTime;
import com.wiki.spring.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class EbookService {

    @Autowired(required = false)
    private EbookMapper ebookMapper;

    @Autowired
    private SnowFlake snowFlake;


    //    @SuppressWarnings(value = "all")
    public PageResp<EbookQueryResp> list(EbookQueryReq req) {

        EbookExample ebookExample = new EbookExample();
        // 设置为时间降序
        ebookExample.setOrderByClause("update_time desc");
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        // 设置模糊查询
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        // 分页
        PageHelper.startPage(req.getPage(), req.getSize());  // 只对第一条查询语句有效
        List<Ebook> ebooksList = ebookMapper.selectByExample(ebookExample);
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooksList);
        List<EbookQueryResp> list = CopyUtil.copyList(ebooksList, EbookQueryResp.class);
        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
//        log.info("pageInfo.getPages {}", pageInfo.getPages());
        // 列表赋值
        return pageResp;
    }

    /*
     **  保存编辑,支持新增和更新
     * */
    public void save(EbookSaveReq req) throws ParseException {
        // 响应参数(req)变成实体(ebook)
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        // 如果没有id,代表新增
        if (ObjectUtils.isEmpty(ebook.getId())) {
            ebook.setCreateTime(NowTime.create());
            ebook.setUpdateTime(NowTime.create());
            // 雪花算法生成一个唯一ID
            ebook.setId(String.valueOf(snowFlake.nextId()));
            ebookMapper.insert(ebook);
        } else { //如果有id,代表更新
            ebook.setUpdateTime(NowTime.create());
            // 更新
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }


    /*
     **  删除书
     * */

    public void delete(Long id) {
        Integer row = ebookMapper.deleteByPrimaryKey(id);
        log.warn("{}", row);
    }


}
