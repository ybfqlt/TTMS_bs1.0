package com.coco.service.impl;

import com.coco.dao.*;
import com.coco.entity.*;
import com.coco.service.OrdersService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Classname OrdersServiceimpl
 * @Description TODO
 * @Date 19-6-10 上午11:23
 * @Created by xns
 */

@Service("OrdersService")
public class OrdersServiceimpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private userMapper usermapper;

    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    /**
    * @Description 将订单加入订单表中
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Result Addorders(Integer userId, Integer scheduleId,List<Reorder> order1){
        Result res = new Result();
        List<Integer> list = new LinkedList<>();
        Schedule schedulee = scheduleMapper.selectByscheduleId(scheduleId);
        for(int i=0;i<order1.size();i++) {
            Seat seat = seatMapper.selectByrowcol(schedulee.getHallId(),order1.get(i).getSeatRow(),order1.get(i).getSeatCol());
            Long ticketId = ticketMapper.selectByscheduleIdseatId(scheduleId,seat.getSeatId());
            //将订单设为购买记录的订单
            Orders order = new Orders(userId,ticketId,Short.parseShort("0"));
            //插入
            ordersMapper.insert(order);
            list.add(ordersMapper.selectByuserIdandticketId(userId,ticketId));
        }
        res.setMes(list);
        res.setJudge(true);
        return res;
    }

    /**
    * @Description 根据名称查询某人的所有订单
    * @return java.util.List<com.coco.entity.Orders>
    *
    **/
    @Override
    public Result orderlist(String name){
        Result res = new Result();
        user uu = usermapper.selectByuserName(name);
        List<Orders> orderlist = ordersMapper.selectByuserId(uu.getUserId());
        if(orderlist == null){
            res.setJudge(false);
            res.setMes("您暂时还没有订单哟，快快取首页买一个吧!!!");
        }
        else{
            res.setJudge(true);
            res.setMes(orderlist);
        }
        return res;
    }

    /**
    * @Description 根据用户名称，返回某人的购买订单数量和退款订单数量
    * @return java.util.Map<java.lang.String,java.lang.Integer>
    *
    **/
    @Override
    public Map<String,Integer> ordercount(String name){
        int recount=0;//退款数量
        int count=0;//购买数量
        user uu = usermapper.selectByuserName(name);
        List<Orders> orderlist = ordersMapper.selectByuserId(uu.getUserId());
        for(int i=0;i<orderlist.size();i++){
            if(orderlist.get(i).getOrdersType()==1){
                count++;
            }
            else{
                recount++;
            }
        }
        Map<String,Integer> ma = new HashMap<>();
        ma.put("recount",recount);
        ma.put("count",count);
        return ma;
    }

    /**
    * @Description 根据订单id更新订单为已经
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean updateOrder(Integer orderId){
        Orders order = ordersMapper.selectByPrimaryKey(orderId);
        if(order.getOrdersType()==0){
            return false;
        }
        else {
            ordersMapper.updateByordersId(orderId);
            return true;
        }
    }

    /**
     * @Description 根据订单id更新订单为已经付款
     * @return java.lang.Boolean
     *
     **/
    @Override
    public Boolean updateOrderf(String di){
        StringBuilder a= new StringBuilder();
        for(int i=0;i<di.length();i++) {
            if(di.charAt(i)!='t') {
                a.append(di.charAt(i));
            }
            else{
                System.out.println(Integer.valueOf(a.toString()));
                Orders order1 = ordersMapper.selectByPrimaryKey(Integer.valueOf(a.toString()));
                Ticket ticket = ticketMapper.selectByPrimaryKey(order1.getTicketId());
                //在票的表中将其修改为已经购买
                ticketMapper.updateByscheduleIdseatId(ticket.getScheduleId(), ticket.getSeatId(), Short.parseShort("0"));
                ordersMapper.updateByordersId(order1.getOrdersId());
                a.setLength(0);
            }
        }
        return true;
    }

    /**
    * @Description 根据id删除订单
    * @return java.lang.Boolean
    *
    **/
    public Boolean deleteByid(String di){
        for(int i=0;i<di.length();i++) {
           ordersMapper.deleteByPrimaryKey(Integer.valueOf(di.charAt(i)));
        }
        return true;
    }

    /**
     * @Description 根据用户id返回用户买过的票的数量，以及未使用的票的数量和看过的电影的数量
     * @return java.util.Map<java.lang.String,java.lang.Integer>
     *
     **/
    @Override
    public Map<String,Integer> getper(Integer userId){
        Map<String, Integer> ma = new HashMap<>();
        List<Orders> listo = ordersMapper.selectByuserIdandbuy(userId);
        if(listo==null){
            ma.put("ticketAll",0);
            ma.put("ticketNo", 0);
            ma.put("movieCount",0);
        }else {
            ma.put("ticketAll", listo.size());
            Date date = new Date();//获取系统时间
            Timestamp timestamp = new Timestamp((date.getTime()));
            int count = 0;
            for (int i = 0; i < listo.size(); i++) {
                Ticket ticket = ticketMapper.selectByPrimaryKey(listo.get(i).getTicketId());
                Schedule schedule = scheduleMapper.selectByscheduleId(ticket.getScheduleId());
                if (schedule.getScheduleEndTime().before(timestamp)) {
                    count++;
                }
            }
            ma.put("movieCount", count);
            ma.put("ticketNo", listo.size() - count);
        }
        return ma;
    }


    /**
    * @Description 根据orderid获取订单
    * @return com.coco.entity.Orders
    *
    **/
    @Override
    public Orders getorderById(Integer orderId){
        return ordersMapper.selectByPrimaryKey(orderId);
    }



    /**
    * @Description 查询个人订单
    * @return com.coco.entity.Reeorder
    *
    **/
    @Override
    public List<Reeorder> selectper(String name){
        List<Reeorder> list = new ArrayList<>();
        user uu = usermapper.selectByuserName(name);
        List<Timestamp> cun = new LinkedList<>();
        List<Orders> list1 = ordersMapper.selectByuserId(uu.getUserId());
        for(int i=0;i<list1.size();i++){
            if(cun.contains(list1.get(i).getOrdersTime())){

            }else {
                cun.add((Timestamp) list1.get(i).getOrdersTime());
            }
        }
        for(int i=0;i<cun.size();i++){
            List<Reticket> list4 = new LinkedList<>();
            List<Long> list2 = ordersMapper.selectBydate((Timestamp)cun.get(i));
            System.out.println(list2);
            Ticket tic = ticketMapper.selectByPrimaryKey(Long.parseLong((String.valueOf(list2.get(0)))));
            Schedule sch = scheduleMapper.selectByscheduleId(tic.getScheduleId());
            for(int j =0;j<list2.size();j++) {
                Reticket ti = new Reticket(Long.parseLong(String.valueOf(list2.get(j))));
                list4.add(ti);
            }
            Reeticket ticke = new Reeticket(list4.size(),list4);
            Reeorder re = new Reeorder(i+1, (sch.getScheduleTicketPrice()).multiply(BigDecimal.valueOf(list4.size())),cun.get(i),ticke);
            list.add(re);
        }
        return list;
    }

    /**
    * @Description 根据id返回某一个订单
    * @return com.coco.entity.Orders
    *
    **/
    @Override
    public Orders reOrder(Integer orderId){
        Orders order = ordersMapper.selectByPrimaryKey(orderId);
        return order;
    }
}
