package com.wiki.spring.controller;


import com.wiki.spring.resp.StatisticResp;
import com.wiki.spring.service.EbookSnapshotService;
import com.wiki.spring.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook-snapshot")
public class EbookSnapshotController {

    @Resource
    private EbookSnapshotService ebookSnapshotService;

    @GetMapping("/get-statistic")
    public R getStatistic() {
        List<StatisticResp> statisticResp = ebookSnapshotService.getStatistic();
        return R.ok(statisticResp);
    }

    @GetMapping("/get-30-statistic")
    public R get30Statistic() {
        List<StatisticResp> statisticResp = ebookSnapshotService.get30Statistic();
        return R.ok(statisticResp);
    }

}
