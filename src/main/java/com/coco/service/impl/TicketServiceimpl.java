package com.coco.service.impl;

import com.coco.dao.HallMapper;
import com.coco.dao.OrdersMapper;
import com.coco.dao.SeatMapper;
import com.coco.dao.TicketMapper;
import com.coco.entity.*;
import com.coco.service.OrdersService;
import com.coco.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname TicketServiceimpl
 * @Description TODO
 * @Date 19-6-9 下午2:16
 * @Created by xns
 */
@Service("TicketService")
public class TicketServiceimpl implements TicketService {
    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private HallMapper hallMapper;

    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    /**
    * @Description 根据演出计划生成票并且插入票的表里
    * @return com.coco.entity.Result
    *
    **/
    @Override
    public Result ProduceticketByschedule(Schedule schedule){
        Result res = new Result();
        Hall hall = hallMapper.selectByPrimaryKey(schedule.getHallId());
        for(int i=1;i<=hall.getHallSeatRow();i++){
            for(int j =1;j<=hall.getHallSeatCol();j++){
                Seat seat = seatMapper.selectByrowcol(i,j);
                if(seat == null){
                    res.setJudge(false);
                    res.setMes("第"+i+"行"+"第"+j+"列的座位不存在!!!");
                    return res;
                }
                if(seat.getSeatStatus()==1){
                    Ticket ticket = new Ticket(seat.getSeatId(),schedule.getScheduleId(),Short.parseShort("1"));
                    ticketMapper.insert(ticket);
                }
            }
        }
        res.setJudge(true);
        res.setMes("并且生成票完毕!!!");
        return res;
    }


    /**
    * @Description 批量更新票的购买信息(根据演出计划id以及座位行号和列号)。比如:同一个人买了好几张票或者一个人只买了一张票
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean UpdateTicket(List<Ticket> lists){
        for(int i=0;i<lists.size();i++){
            ticketMapper.updateByscheduleIdseatId(lists.get(i).getScheduleId(),lists.get(i).getSeatId(),Short.parseShort("0"));
        }
        return true;
    }


    /**
    * @Description 根据演出计划id删除根据此演出计划生成的演出票
    * @return com.coco.entity.Result
    *
    **/
    @Override
    public Result DeletescheduleByscheduleId(Integer scheduleId){
        Result res = new Result();
        List<Ticket> lists = ticketMapper.sealectByscheduleId(scheduleId);
        int count = 0;
        for(int i=0;i<lists.size();i++){
            if(lists.get(i).getTicketStatus()==0){
                count++;
                res.setJudge(false);
            }
        }
        if(count == 0){
            ticketMapper.deleteByscheduleId(scheduleId);
            res.setJudge(true);
            res.setMes("删除成功");
        }
        else{
            res.setMes("此演出计划有"+count+"张票已经售出!!!,不能删除。");
        }
        return res;
    }

    /**
    * @Description 根据订单id将票的信息更新为可购买
    * @return java.lang.Boolean
    *
    **/
    public Boolean UpdateTicketByorderid(Integer orderId){
        Orders orders = ordersMapper.selectByPrimaryKey(orderId);
        if(ticketMapper.updateByticketId(orders.getTicketId())!=0){
            return true;
        }
        else{
            return false;
        }
    }
}
