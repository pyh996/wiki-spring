package com.wiki.spring.controller;

import com.wiki.spring.domain.Test;
import com.wiki.spring.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class testController {
    @Autowired
    private TestService testService;

    @GetMapping("/hello")
    public String hello() {
        return "123";
    }


    @GetMapping("/test/list")
    public List<Test> list() {
        return testService.list();
    }
}
