package com.coco.dao;

import com.coco.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface OrdersMapper {
    int deleteByPrimaryKey(Integer ordersId);

    int insert(Orders record);

    Orders selectByPrimaryKey(Integer ordersId);

    //根据用户的id查询用户的所有账单
    List<Orders> selectByuserId(Integer userId);

    //根据订单id更新订单为已经购买
    int updateByordersId(Integer ordersId);

    //通过userId查询某人买过的票
    List<Orders> selectByuserIdandbuy(Integer user);

    List<Orders> selectAll();

    int updateByPrimaryKey(Orders record);

    List<Long> selectBydate(@Param("time")Timestamp time);

    //根据票的id和用户id返回订单id
    Integer selectByuserIdandticketId(@Param("userId") Integer userId,@Param("ticketId") Long ticketId);
}