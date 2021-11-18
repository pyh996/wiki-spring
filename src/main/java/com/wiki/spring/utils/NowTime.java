package com.wiki.spring.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NowTime {
    public static Date create() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String date = simpleDateFormat.format(new java.util.Date());
        return simpleDateFormat.parse(date);
    }
}
