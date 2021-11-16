package com.wiki.spring.controller;

import com.wiki.spring.domain.Demo;
import com.wiki.spring.domain.Test;
import com.wiki.spring.service.DemoService;
import com.wiki.spring.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("/demo")
    public List<Demo> list() {
        return demoService.getSelect();
    }
}
