package com.wiki.spring.controller;


import com.wiki.spring.req.CategoryQueryReq;
import com.wiki.spring.req.CategorySaveReq;
import com.wiki.spring.service.CategoryService;
import com.wiki.spring.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public R list(@Valid CategoryQueryReq req) { // @ModelAttribute  或者必须保证有set和get
        return R.ok(categoryService.list(req));
    }


    @PostMapping("/save")
    public R save(@Valid  @RequestBody CategorySaveReq req) throws ParseException { // @ModelAttribute  或者必须保证有set和get
        categoryService.save(req);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")

    public R delete(@PathVariable Long id) { // @ModelAttribute  或者必须保证有set和get
        log.warn("id------->{}", id);
        categoryService.delete(id);
        return R.ok(String.format("%s 删除成功", id), "");
    }

}
