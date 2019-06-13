package com.coco.dao;

import com.coco.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface ScheduleMapper {
    int deleteByPrimaryKey(Integer scheduleId);

    int insert(Schedule record);

    //根据演出计划id查询演出计划
    Schedule selectByscheduleId(Integer scheduleId);

    //根据剧目id查询演出计划
    List<Schedule> selectBymovieId(Integer movieId);

    //根据演出厅id查询演出计划
    List<Schedule> selectByhallId(Integer hallId);

    //根据演出厅id查看开始时间是否与待添加计划冲突的计划
    List<Schedule> selectByhallIdstarttime(@Param("hallId") Integer hallId, @Param("starttime") String starttime, @Param("endtime")String endtime);

    //根据演出厅id查看结束时间是否与待添加计划冲突的计划
    List<Schedule> selectByhallIdendtime(@Param("hallId") Integer hallId, @Param("starttime") String starttime, @Param("endtime")String endtime);

    //根据剧目id和日期查询有关演出计划
    List<Schedule> selectBymovieIdtime(@Param("movieId") Integer movieId,@Param("starttime") String starttime, @Param("endtime") String endtime);

    //根据演出厅id及开始时间获取演出计划
    Integer selectBystarttime(@Param("hallId") Integer hallId, @Param("scheduleStartTime") Timestamp scheduleStartTime);

    List<Schedule> selectAll();

    int updateByScheduleId(Schedule record);
}