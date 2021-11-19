package com.wiki.spring.controller;


import com.wiki.spring.req.UserQueryReq;
import com.wiki.spring.req.UserSaveReq;
import com.wiki.spring.service.UserService;
import com.wiki.spring.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public R list(@Valid UserQueryReq req) { // @ModelAttribute  或者必须保证有set和get
        return R.ok(userService.list(req));
    }


    @PostMapping("/save")
    public R save(@Valid  @RequestBody UserSaveReq req) throws ParseException { // @ModelAttribute  或者必须保证有set和get
        userService.save(req);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")

    public R delete(@PathVariable Long id) { // @ModelAttribute  或者必须保证有set和get
        log.warn("id------->{}", id);
        userService.delete(id);
        return R.ok(String.format("%s 删除成功", id), "");
    }



}
