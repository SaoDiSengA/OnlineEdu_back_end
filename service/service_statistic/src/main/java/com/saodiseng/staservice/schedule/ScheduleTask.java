package com.saodiseng.staservice.schedule;

import com.saodiseng.staservice.service.StatisticsDailyService;
import com.saodiseng.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    //每天凌晨1点执行，吧前一天数据进行查询添加
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2(){
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(),-1)));
    }

//    //每隔5s执行一次
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void task1(){
//        System.out.println("=============task1执行了");
//    }


}
