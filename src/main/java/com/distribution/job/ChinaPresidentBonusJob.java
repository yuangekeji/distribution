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
public class ChinaPresidentBonusJob {

    @Autowired
    private ChinaPresidentBonusService chinaPresidentBonusService;

    /**
     * 全国董事奖日job
     * @author shiqing
     * @date 2017.09.05
     */
    //@Scheduled(cron = "0/30 * * * * *")  //测试用
    @Scheduled(cron ="0 0 1 * * ?" )//每天早上1点钟执行
    public void addChinaPresidentBonusByDay() {
        Map map = chinaPresidentBonusService.addChinaPresidentBonusByDay();
        chinaPresidentBonusService.jobLogsByDay(map);
    }

    /**
     * 全国董事奖月job
     * @author shiqing
     * @date 2017.09.07
     */
    //@Scheduled(cron = "0/30 * * * * *")  //测试用
    @Scheduled(cron ="0 0 2 1 * ?" )//每月1号早上1点钟执行
    public void addChinaPresidentBonusByMonth() {
        Map map = chinaPresidentBonusService.addChinaPresidentBonusByMonth();
        chinaPresidentBonusService.jobLogsByMonth(map);
    }
}
