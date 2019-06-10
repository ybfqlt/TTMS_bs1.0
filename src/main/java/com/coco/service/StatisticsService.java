package com.coco.service;

/**
 * @Classname StatisticsService
 * @Description TODO
 * @Date 19-6-10 下午5:19
 * @Created by xns
 */
public interface StatisticsService {
    //根据scheduleId及买的票的个数更新销售量和销售额
    Boolean updateStatistics(Long n,int scheduleId);

}
