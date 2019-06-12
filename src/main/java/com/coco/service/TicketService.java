package com.coco.service;

import com.coco.entity.Result;
import com.coco.entity.Schedule;
import com.coco.entity.Ticket;

import java.util.List;
import java.util.Map;

/**
 * @Classname TicketService
 * @Description TODO
 * @Date 19-6-9 下午2:16
 * @Created by xns
 */
public interface TicketService {
    //根据演出计划生成票
    Result ProduceticketByschedule(Schedule schedule);

    //批量更新票的信息(根据演出计划id以及座位行号和列号)
    Boolean UpdateTicket(List<Ticket> lists);

    //根据演出计划id删除根据此演出计划生成的演出票
    Result DeletescheduleByscheduleId(Integer scheduleId);

    //根据订单id将已经退款的票的状态更新为可购买
    Boolean UpdateTicketByorderid(Integer orderId);

}
