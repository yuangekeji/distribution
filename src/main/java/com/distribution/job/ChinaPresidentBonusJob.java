package com.distribution.job;

import com.distribution.service.ChinaPresidentBonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Created by shiqing on 2017/9/5.
 */
@Component
public class ChinaPresidentBonusJob {

    @Autowired
    private ChinaPresidentBonusService chinaPresidentBonusService;
    @Scheduled(cron = "0/10 * * * * *")
    //@Scheduled(cron ="0 0 1 * * ?" )//每天早上1点钟执行
    public void addChinaPresidentBonusByDay() {
        chinaPresidentBonusService.addChinaPresidentBonusByDay();
    }
}
