package com.coco.service.impl;

import com.coco.dao.MovieMapper;
import com.coco.dao.SalestatisticsMapper;
import com.coco.dao.ScheduleMapper;
import com.coco.entity.Salestatistics;
import com.coco.entity.Schedule;
import com.coco.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Classname StatisticsServiceimpl
 * @Description TODO
 * @Date 19-6-10 下午5:19
 * @Created by xns
 */
@Service("StatisticsService")
public class StatisticsServiceimpl implements StatisticsService {
    @Autowired
    private SalestatisticsMapper salestatisticsMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    /**
    * @Description 更新票房和销售额
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean updateStatistics(Long n,int scheduleId){
        Schedule schedule = scheduleMapper.selectByscheduleId(scheduleId);
        Salestatistics sa = salestatisticsMapper.selectBymovieId(schedule.getMovieId());
        Long count = sa.getSaleCount()+n;
        BigDecimal countmoney = sa.getSaleMoneyCount().add(BigDecimal.valueOf(n).multiply(schedule.getScheduleTicketPrice()));
        sa.setSaleCount(count);
        sa.setSaleMoneyCount(countmoney);
        salestatisticsMapper.updateBymovieId(sa);
        return true;
    }
}
