package com.coco.service;

import com.coco.entity.Result;
import com.coco.entity.Schedule;

import java.sql.Timestamp;
import java.text.ParseException;

/**
 * @Classname ScheduleService
 * @Description TODO
 * @Date 19-6-8 下午4:42
 * @Created by xns
 */
public interface ScheduleService {
    //添加演出计划
    Result addSchedule(Schedule schedule) throws ParseException;

    //根据剧目名称查询演出计划
    Result selectScheduleBymovieTitle(String movieTitle);

    //查询所有演出计划
    Result selectAllSchedule();

    //根据时间和剧目名称查询演出计划
    Result selectScheduleBydatetime(String date,String movieTitle);

    //根据时间和演出厅id查询演出计划
    Result selectScheduleByhallId(Integer id,String date);

    //删除演出计划
    Boolean deleteSchedule(Integer scheduleId);

    //根据scheduleId返回演出计划的信息
    Schedule GetscheduleByscheduleId(Integer scheduleId);

    //修改演出计划
    Boolean modifySchedule(Schedule schedule);

    //根据演出厅id及演出开始时间返回演出计划
    Integer get(Integer hallId, Timestamp time);
}
