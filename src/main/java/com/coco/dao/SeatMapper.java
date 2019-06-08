package com.coco.dao;

import com.coco.entity.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SeatMapper {
    int deleteByPrimaryKey(Integer seatId);

    //插入
    int insert(Seat record);

    //根据演出厅id返回此演出厅的所有座位
    List<Seat> selectByHallId(Integer hallId);

    List<Seat> selectAll();

    int updateByPrimaryKey(Seat record);
}