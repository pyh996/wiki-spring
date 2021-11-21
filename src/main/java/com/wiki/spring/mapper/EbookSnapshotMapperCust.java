package com.wiki.spring.mapper;

import com.wiki.spring.resp.StatisticResp;

import java.util.List;

public interface EbookSnapshotMapperCust {

    public void updateTodaySum();

    public void initSnapshot();

    public void updateToday();

    List<StatisticResp> getStatistic();

    List<StatisticResp> get30Statistic();
}
