package com.wiki.spring.controller;


import com.wiki.spring.req.DocQueryReq;
import com.wiki.spring.req.DocSaveReq;
import com.wiki.spring.resp.DocQueryResp;
import com.wiki.spring.resp.PageResp;
import com.wiki.spring.service.DocService;
import com.wiki.spring.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/doc")
public class DocController {
    @Autowired
    private DocService docService;

    @GetMapping("/all")
    public R list(@Valid DocQueryReq req) { // @ModelAttribute  或者必须保证有set和get
        return R.ok(docService.list(req));
    }

    @GetMapping("/all/{ebookId}")
    public R all(@PathVariable Long ebookId) {
        PageResp<DocQueryResp> list = docService.all(ebookId);
        return R.ok(list);
    }

    @PostMapping("/save")
    public R save(@Valid @RequestBody DocSaveReq req) throws ParseException { // @ModelAttribute  或者必须保证有set和get
        docService.save(req);
        return R.ok();
    }

    @DeleteMapping("/delete/{idsStr}")
    public R delete(@PathVariable String idsStr) {

        List<String> list = Arrays.asList(idsStr.split(","));
        docService.delete(list);
        return R.ok(String.format("%s 删除成功", idsStr), "");
    }

    @GetMapping("/find-content/{id}")
    public R findContent(@PathVariable Long id) {

        return R.ok(String.format("%s 查询文章成功", id), docService.findContent(id));
    }


    @GetMapping("/vote/{id}")
    public R vote( @PathVariable(name = "id", required = true) Long id) {
        docService.vote(id);
        return R.ok(String.format("%s 点赞成功", id));
    }

}
