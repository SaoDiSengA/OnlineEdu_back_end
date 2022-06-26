package com.saodiseng.staservice.service;

import com.saodiseng.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-06-26
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    //统计某一天的注册人数,生成统计数据
    void registerCount(String day);
}
