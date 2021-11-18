package com.wiki.spring.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wiki.spring.domain.Category;
import com.wiki.spring.domain.CategoryExample;
import com.wiki.spring.mapper.CategoryMapper;
import com.wiki.spring.req.CategoryQueryReq;
import com.wiki.spring.req.CategorySaveReq;
import com.wiki.spring.resp.CategoryQueryResp;
import com.wiki.spring.resp.PageResp;
import com.wiki.spring.utils.CopyUtil;
import com.wiki.spring.utils.NowTime;
import com.wiki.spring.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.util.List;

@Slf4j
@Service
public class CategoryService {

    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    @Autowired
    private SnowFlake snowFlake;


    //    @SuppressWarnings(value = "all")
    public PageResp<CategoryQueryResp> list(CategoryQueryReq req) {

        CategoryExample categoryExample = new CategoryExample();
        // 设置为时间降序
        categoryExample.setOrderByClause("sort desc");
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        // 分页
        PageHelper.startPage(req.getPage(), req.getSize());  // 只对第一条查询语句有效
        List<Category> categorysList = categoryMapper.selectByExample(categoryExample);
        PageInfo<Category> pageInfo = new PageInfo<>(categorysList);
        List<CategoryQueryResp> list = CopyUtil.copyList(categorysList, CategoryQueryResp.class);
        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
//        log.info("pageInfo.getPages {}", pageInfo.getPages());
        // 列表赋值
        return pageResp;
    }

    /*
     **  保存编辑,支持新增和更新
     * */
    public void save(CategorySaveReq req) throws ParseException {
        // 响应参数(req)变成实体(category)
        Category category = CopyUtil.copy(req, Category.class);
        // 如果没有id,代表新增
        if (ObjectUtils.isEmpty(category.getId())) {
            // 雪花算法生成一个唯一ID
            category.setId(String.valueOf(snowFlake.nextId()));
            categoryMapper.insert(category);
        } else { //如果有id,代表更新
            // 更新
            categoryMapper.updateByPrimaryKey(category);
        }
    }


    /*
     **  删除书
     * */

    public void delete(Long id) {
        Integer row = categoryMapper.deleteByPrimaryKey(id);
        log.warn("{}", row);
    }


}
