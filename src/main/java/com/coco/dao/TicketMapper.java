package com.coco.dao;

import com.coco.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TicketMapper {
    int deleteByPrimaryKey(Long ticketId);

    int insert(Ticket record);

    Ticket selectByPrimaryKey(Long ticketId);

    List<Ticket> selectAll();

    //根据演出计划id返回票
    List<Ticket> sealectByscheduleId(Integer scheduleId);

    //根据演出计划删除相关票
    int deleteByscheduleId(Integer scheduleId);

    //根据jihuaid和seaid更新票的状态
    int updateByscheduleIdseatId(@Param("scheduleId") Integer scheduleId,@Param("seatId")Integer seatId,@Param("ticketStatus") short ticketStatus);

    int updateByPrimaryKey(Ticket record);
}