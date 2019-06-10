package com.coco.dao;

import com.coco.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersMapper {
    int deleteByPrimaryKey(Integer ordersId);

    int insert(Orders record);

    Orders selectByPrimaryKey(Integer ordersId);

    //根据用户的id查询用户的所有账单
    List<Orders> selectByuserId(Integer userId);

    //根据订单id更新订单为已经退款
    int updateByordersId(Integer ordersId);

    List<Orders> selectAll();

    int updateByPrimaryKey(Orders record);
}