package com.wiki.spring.controller;


import com.alibaba.fastjson.JSONObject;
import com.wiki.spring.req.UserLoginReq;
import com.wiki.spring.req.UserQueryReq;
import com.wiki.spring.req.UserResetPasswordReq;
import com.wiki.spring.req.UserSaveReq;
import com.wiki.spring.resp.UserLoginResp;
import com.wiki.spring.service.UserService;
import com.wiki.spring.utils.R;
import com.wiki.spring.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SnowFlake snowFlake;

    @GetMapping("/list")
    public R list(@Valid UserQueryReq req) { // @ModelAttribute  或者必须保证有set和get
        return R.ok(userService.list(req));
    }


    @PostMapping("/save")
    public R save(@Valid @RequestBody UserSaveReq req) { // @ModelAttribute  或者必须保证有set和get
        // 密码加密
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        userService.save(req);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")

    public R delete(@PathVariable Long id) { // @ModelAttribute  或者必须保证有set和get
        log.warn("id------->{}", id);
        userService.delete(id);
        return R.ok(String.format("%s 删除成功", id), "");
    }

    @PostMapping("/reset-password")
    public R resetPassword(@Valid @RequestBody UserResetPasswordReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        userService.resetPassword(req);
        return R.ok("密码重置", "");
    }


    @PostMapping("/login")
    public R login(@Valid @RequestBody UserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        UserLoginResp userLoginResp = userService.login(req);
        Long token = snowFlake.nextId();
        userLoginResp.setToken(token.toString());
        // 把key(id)-value(用户信息) 存在redis当中
        log.info("生成单点登录token：并放入redis中,{},{}", token, JSONObject.toJSONString(userLoginResp));
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginResp), 3600 * 24, TimeUnit.SECONDS);
        return R.ok("登录成功", userLoginResp);
    }

    @GetMapping("/logout/{token}")
    public R logout(@PathVariable String token) {
        log.info("生成单点登录token：{}，并放入redis中", redisTemplate.boundValueOps(token).get());
        log.info("mykey--------------->{}", redisTemplate.boundValueOps("mykey").get());
        redisTemplate.delete(token);
        return R.ok("退出成功", "");
    }

}
