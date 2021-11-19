package com.wiki.spring.controller;


import com.wiki.spring.req.EbookQueryReq;

import com.wiki.spring.req.EbookSaveReq;
import com.wiki.spring.service.EbookService;
import com.wiki.spring.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@Slf4j
@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Autowired
    private EbookService ebookService;

    @GetMapping("/list")
    public R list(@Valid EbookQueryReq req) { // @ModelAttribute  或者必须保证有set和get
        return R.ok(ebookService.list(req));
    }


    @PostMapping("/save")
    public R save(@Valid  @RequestBody EbookSaveReq req) throws ParseException { // @ModelAttribute  或者必须保证有set和get
        ebookService.save(req);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")

    public R delete(@PathVariable Long id) { // @ModelAttribute  或者必须保证有set和get
        log.warn("id------->{}", id);
        ebookService.delete(id);
        return R.ok(String.format("%s 删除成功", id), "");
    }



}
