package com.distribution.job;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Created by jingxin on 2017/9/5.
 */
@Component
public class JobTest {

    //5秒钟执行一次
    @Scheduled(cron = "0/5 * * * * *")
    public void process() {
        System.out.println("job run");
    }

}
