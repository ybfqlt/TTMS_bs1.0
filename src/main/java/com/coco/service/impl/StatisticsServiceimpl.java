package com.coco.service.impl;

import com.coco.dao.*;
import com.coco.entity.*;
import com.coco.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private MovieMapper movieMapper;

    /**
    * @Description 更新票房和销售额
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean updateStatistics(int n,int scheduleId){
        Schedule schedule = scheduleMapper.selectByscheduleId(scheduleId);
        Salestatistics sa = salestatisticsMapper.selectBymovieId(schedule.getMovieId());
        if(sa == null){
            return false;
        }
        Long count = sa.getSaleCount()+n;
        BigDecimal countmoney = sa.getSaleMoneyCount().add(BigDecimal.valueOf(n).multiply(schedule.getScheduleTicketPrice()));
        sa.setSaleCount(count);
        sa.setSaleMoneyCount(countmoney);
        salestatisticsMapper.updateBymovieId(sa);
        return true;
    }

    /**
    * @Description 根据scheduleId更新销售量和销售额
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean updaterefundStatistics(int orderId){
        Orders order = ordersMapper.selectByPrimaryKey(orderId);
        Ticket ticket = ticketMapper.selectByPrimaryKey(order.getTicketId());
        Schedule schedule = scheduleMapper.selectByscheduleId(ticket.getScheduleId());
        Salestatistics sa = salestatisticsMapper.selectBymovieId(schedule.getMovieId());
        if(sa == null){
            return false;
        }
        Long count = sa.getSaleCount()-1;
        BigDecimal countmoney = sa.getSaleMoneyCount().subtract(schedule.getScheduleTicketPrice());
        sa.setSaleCount(count);
        sa.setSaleMoneyCount(countmoney);
        salestatisticsMapper.updateBymovieId(sa);
        return true;
    }


    /**
    * @Description 返回每个剧目及其票房和销售额
    * @return java.util.List<com.coco.entity.Salestatistics>
    *
    **/
    public Result returnStatistics(){
        Result res = new Result();
        List<Salestatistics> lists = salestatisticsMapper.selectAll();
        System.out.println(lists);
        if(lists == null){
            res.setJudge(false);
            res.setMes("暂时没有任何剧目");
        }
        else {
            List<Restatistics> list = new ArrayList<Restatistics>();
            for (int i = 0; i < lists.size(); i++) {
                Movie movie = movieMapper.selectBymovieId(lists.get(i).getMovieId());
                list.add(new Restatistics(movie.getMovieTitle(), lists.get(i).getSaleCount(), lists.get(i).getSaleMoneyCount()));
            }
            res.setJudge(true);
            res.setMes(list);
        }
        return res;
    }
}
