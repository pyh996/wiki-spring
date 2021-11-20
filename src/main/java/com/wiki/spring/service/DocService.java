package com.wiki.spring.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wiki.spring.domain.Content;
import com.wiki.spring.domain.Doc;
import com.wiki.spring.domain.DocExample;
import com.wiki.spring.mapper.ContentMapper;
import com.wiki.spring.mapper.DocMapper;
import com.wiki.spring.mapper.DocMapperCust;
import com.wiki.spring.req.DocQueryReq;
import com.wiki.spring.req.DocSaveReq;
import com.wiki.spring.resp.DocQueryResp;
import com.wiki.spring.resp.PageResp;
import com.wiki.spring.utils.CopyUtil;
import com.wiki.spring.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.util.List;

@Slf4j
@Service
public class DocService {

    @Autowired(required = false)
    private DocMapper docMapper;

    @Autowired(required = false)
    private ContentMapper contentMapper;

    @Autowired
    private SnowFlake snowFlake;


    @Autowired
    private DocMapperCust docMapperCust;


    //    @SuppressWarnings(value = "all")
    public PageResp<DocQueryResp> list(DocQueryReq req) {

        DocExample docExample = new DocExample();
        // 设置为时间降序
        docExample.setOrderByClause("sort asc");
        DocExample.Criteria criteria = docExample.createCriteria();
        // 分页
        PageHelper.startPage(req.getPage(), req.getSize());  // 只对第一条查询语句有效
        List<Doc> docsList = docMapper.selectByExample(docExample);
        PageInfo<Doc> pageInfo = new PageInfo<>(docsList);
        List<DocQueryResp> list = CopyUtil.copyList(docsList, DocQueryResp.class);
        PageResp<DocQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
//        log.info("pageInfo.getPages {}", pageInfo.getPages());
        // 列表赋值
        return pageResp;
    }

    public PageResp<DocQueryResp> all(Long ebookId) {
        DocExample docExample = new DocExample();
        docExample.createCriteria().andEbookIdEqualTo(ebookId);
        docExample.setOrderByClause("sort asc");
        List<Doc> docList = docMapper.selectByExample(docExample);
        // 列表复制
        List<DocQueryResp> list = CopyUtil.copyList(docList, DocQueryResp.class);
        PageResp<DocQueryResp> pageResp = new PageResp<>();
        pageResp.setList(list);

        return pageResp;
    }

    /*
     **  保存编辑,支持新增和更新
     * */
    public void save(DocSaveReq req) throws ParseException {
        // 响应参数(req)变成实体(doc)
        Doc doc = CopyUtil.copy(req, Doc.class);
        Content content = CopyUtil.copy(req, Content.class);  // 生成一个content的实体类
        log.warn("doc.getId() {} -------{} ", doc.getId(), req.getId());
        // 如果没有id,代表新增
        if (ObjectUtils.isEmpty(doc.getId())) {
            log.warn("IFFFFFFFFFFFFFFF {}", doc.getId());
            // 雪花算法生成一个唯一ID
            doc.setId(String.valueOf(snowFlake.nextId()));
            doc.setViewCount(0);
            doc.setVoteCount(0);
            docMapper.insert(doc);
            // ccontent表
            content.setId(doc.getId());
            contentMapper.insert(content);
        } else { //如果有id,代表更新
            log.warn("ELSEEEEEEEEEEEEEEEE {}", doc.getId());
            // 更新
            docMapper.updateByPrimaryKey(doc);
            // 关于大字段的更新
            int count = contentMapper.updateByPrimaryKeyWithBLOBs(content);
            // 如果没有更新成功(说明表里面没有记录),就插入
            if (count == 0) {
                contentMapper.insert(content);
            }
        }
    }


    /*
     **  删除书
     * */

    public void delete(List<String> ids) {
        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();
        criteria.andIdIn(ids);
        docMapper.deleteByExample(docExample);
    }

    public void delete(Long id) {
        Integer row = docMapper.deleteByPrimaryKey(id);
        log.warn("{}", row);
    }

    // 查找文档内容
    public String findContent(Long id) {
        Content content = contentMapper.selectByPrimaryKey(id);
        // 文档阅读数+1
        docMapperCust.increaseViewCount(id);
        if (ObjectUtils.isEmpty(content)) {  // 判断防止空指针异常
            return "";
        } else {
            return content.getContent();  // 返回内容
        }
    }

//    public Integer DeleteContent(Long id) {
//        int row = contentMapper.deleteByPrimaryKey(id);
//        return row;
//    }

}
