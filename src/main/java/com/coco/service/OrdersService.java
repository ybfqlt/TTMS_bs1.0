package com.coco.service;

import com.coco.entity.Orders;
import com.coco.entity.Reeorder;
import com.coco.entity.Reorder;
import com.coco.entity.Result;

import java.util.List;
import java.util.Map;

/**
 * @Classname OrdersService
 * @Description TODO
 * @Date 19-6-10 上午11:23
 * @Created by xns
 */
public interface OrdersService {

    //将订单加入订单表中
    Result Addorders(Integer userId, Integer scheduleId,List<Reorder> order);

    //根据名称查询某人的所有订单
    Result orderlist(String name);

    //根据用户名称，返回某人的购买订单数量和退款订单数量
    Map<String,Integer> ordercount(String name);

    //根据订单id更新订单为已经退款
    Boolean updateOrder(Integer orderId);

    //根据用户id返回用户买过的票的数量，以及未使用的票的数量和看过的电影的数量
    Map<String,Integer> getper(Integer userId);

    //根据orderid获取订单
    Orders getorderById(Integer orderId);

    Boolean updateOrderf(String di);

    //根据id删除订单
    Boolean deleteByid(String di);

    //查询个人订单
    List<Reeorder> selectper(String name);
}
