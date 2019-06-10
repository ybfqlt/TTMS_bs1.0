package com.coco.service.impl;

import com.coco.dao.OrdersMapper;
import com.coco.dao.SeatMapper;
import com.coco.dao.TicketMapper;
import com.coco.dao.userMapper;
import com.coco.entity.*;
import com.coco.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
    * @Description 将订单加入订单表中
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean Addorders(Integer userId, List<Reorder> order1){
        for(int i=0;i<order1.size();i++) {
            Seat seat = seatMapper.selectByrowcol(order1.get(i).getSeatRow(),order1.get(i).getSeatCol());
            Long ticketId = ticketMapper.selectByscheduleIdseatId(order1.get(i).getScheduleId(),seat.getSeatId());
            //在票的表中将其修改为已经购买
            ticketMapper.updateByscheduleIdseatId(order1.get(i).getScheduleId(),seat.getSeatId(),Short.parseShort("0"));
            //将订单设为购买记录的订单
            Orders order = new Orders(userId,ticketId,Short.parseShort("1"));
            //插入
            ordersMapper.insert(order);
        }
        return true;
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
    * @Description 根据订单id更新订单为已经退款
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
}
