package com.distribution.job;

import com.distribution.service.ChinaPresidentBonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * Created by shiqing on 2017/9/5.
 */
@Component
public class ChinaPresidentBonusDayJob {

    @Autowired
    private ChinaPresidentBonusService chinaPresidentBonusService;
    //@Scheduled(cron = "0/30 * * * * *")  //测试用
    @Scheduled(cron ="0 0 1 * * ?" )//每天早上1点钟执行
    public void addChinaPresidentBonusByDay() {
        Map map = chinaPresidentBonusService.addChinaPresidentBonusByDay();
        chinaPresidentBonusService.jobLogs(map);
    }
}
