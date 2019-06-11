package com.coco.service;

import com.coco.entity.Result;

/**
 * @Classname StatisticsService
 * @Description TODO
 * @Date 19-6-10 下午5:19
 * @Created by xns
 */
public interface StatisticsService {
    //根据scheduleId及买的票的个数更新销售量和销售额
    Boolean updateStatistics(int n,int scheduleId);

    //根据orderId更新销售量和销售额
    Boolean updaterefundStatistics(int orderId);

    //返回每个剧目及其票房和销售额
    Result returnStatistics();

}
