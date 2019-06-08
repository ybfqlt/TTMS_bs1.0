package com.coco.dao;

import com.coco.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ScheduleMapper {
    int deleteByPrimaryKey(Integer scheduleId);

    int insert(Schedule record);

    Schedule selectByPrimaryKey(Integer scheduleId);

    List<Schedule> selectAll();

    //根据演出厅id查询演出计划
    List<Schedule> selectByhallId(Integer hallId);

    //根据演出厅id查看开始时间是否与待添加计划冲突的计划
    List<Schedule> selectByhallIdstarttime(@Param("hallId") Integer hallId, @Param("starttime") String starttime, @Param("endtime")String endtime);

    //根据演出厅id查看结束时间是否与待添加计划冲突的计划
    List<Schedule> selectByhallIdendtime(@Param("hallId") Integer hallId, @Param("starttime") String starttime, @Param("endtime")String endtime);

     int updateByPrimaryKey(Schedule record);
}