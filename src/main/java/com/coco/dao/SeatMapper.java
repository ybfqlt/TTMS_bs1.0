package com.coco.dao;

import com.coco.entity.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SeatMapper {
    int deleteByPrimaryKey(Integer seatId);

    //插入
    int insert(Seat record);

    //根据座位的行列号返回座位信息
    Seat selectByrowcol(@Param("hallId") Integer hallId,@Param("seatRow") Integer setRow, @Param("seatCol") Integer seatCol);

    Seat selectByrowcol2(@Param("seatRow") Integer setRow, @Param("seatCol") Integer seatCol);
    //根据演出厅id返回此演出厅的所有座位
    List<Seat> selectByHallId(Integer hallId);

    List<Seat> selectAll();

    Seat selectByseatId(@Param("seatId") Integer seatId);

    int update(@Param("hallId") Integer hallId,@Param("seatRow") Integer seatRow,@Param("seatCol")Integer seatCol,@Param("seatStatus") Short seatStatus);
}