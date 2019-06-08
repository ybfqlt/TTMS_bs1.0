package com.coco.service;

import com.coco.entity.Result;
import com.coco.entity.Schedule;

import java.text.ParseException;

/**
 * @Classname ScheduleService
 * @Description TODO
 * @Date 19-6-8 下午4:42
 * @Created by xns
 */
public interface ScheduleService {
    //添加演出计划
    Boolean addSchedule(Schedule schedule) throws ParseException;

    //根据剧目名称查询演出计划
    Result selectScheduleBymovieId(Integer movieId);

    //查询所有演出计划
    Result selectAllSchedule();

    //根据时间和剧目名称查询演出计划
    Result selectScheduleBydatetime(String date,String movieTitle);

    //根据时间和演出厅id查询演出计划
    Result selectScheduleByhallId(Integer id,String date);

    //删除演出计划
    Boolean deleteSchedule(Integer scheduleId);

    //修改演出计划
    Boolean modifySchedule(Schedule schedule);
}
