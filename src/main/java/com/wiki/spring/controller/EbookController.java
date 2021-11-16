package com.wiki.spring.controller;

import com.wiki.spring.domain.Ebook;
import com.wiki.spring.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Autowired
    private EbookService ebookService;

    @GetMapping("/list")
    public List<Ebook> list() {
        return ebookService.getSelect();
    }
}
