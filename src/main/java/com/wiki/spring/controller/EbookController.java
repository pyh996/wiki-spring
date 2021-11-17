package com.wiki.spring.controller;


import com.wiki.spring.req.EbookQueryReq;

import com.wiki.spring.service.EbookService;
import com.wiki.spring.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Autowired
    private EbookService ebookService;

    @GetMapping("/list")

    public R list(EbookQueryReq req) { // @ModelAttribute  或者必须保证有set和get
        System.out.println("name" + req.getName());

        return R.ok(ebookService.list(req));
    }

}
