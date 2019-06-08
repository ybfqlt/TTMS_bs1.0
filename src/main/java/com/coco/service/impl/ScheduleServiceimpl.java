package com.coco.service.impl;

import com.coco.dao.MovieMapper;
import com.coco.dao.ScheduleMapper;
import com.coco.entity.Movie;
import com.coco.entity.Result;
import com.coco.entity.Schedule;
import com.coco.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @Classname ScheduleServiceimpl
 * @Description TODO
 * @Date 19-6-8 下午4:42
 * @Created by xns
 */
@Service("ScheduleService")
public class ScheduleServiceimpl implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private MovieMapper movieMapper;

    /**
    * @Description 添加演出计划
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean addSchedule(Schedule schedule) throws ParseException {
        Movie movie = movieMapper.selectBymovieId(schedule.getMovieId());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar endtime = Calendar.getInstance();
        endtime.setTime(schedule.getScheduleStartTime());
        endtime.add(Calendar.MINUTE,movie.getMovieRuntime());
        Timestamp enddate = Timestamp.valueOf(df.format(endtime.getTime()));//string转换为Timestamp类型
        schedule.setScheduleEndTime(enddate);

        //留出入场时间
        Calendar beforestart = Calendar.getInstance();
        beforestart.setTime(schedule.getScheduleStartTime());
        beforestart.add(Calendar.MINUTE,-30);

        //加上退场时间
        Calendar afterend = Calendar.getInstance();
        afterend.setTime(schedule.getScheduleEndTime());
        afterend.add(Calendar.MINUTE,20);

        List<Schedule> schedules = scheduleMapper.selectByhallIdstarttime(schedule.getHallId(),df.format(beforestart),df.format(afterend));
        if(schedules!=null){
            return false;
        }
        else{
            List<Schedule> scheduless = scheduleMapper.selectByhallIdendtime(schedule.getHallId(),df.format(beforestart),df.format(afterend));
            if(scheduless!=null){
                return false;
            }
            else{
                scheduleMapper.insert(schedule);
                return true;
            }
        }

    }

    //根据剧目名称查询演出计划
    @Override
    public Result selectScheduleBymovieId(Integer movieId){
        Result res = new Result( );
        return res;
    }

    //查询所有演出计划
    @Override
    public Result selectAllSchedule(){
        Result res = new Result();
        return res;
    }

    //根据时间和剧目名称查询演出计划
    @Override
    public Result selectScheduleBydatetime(String date,String movieTitle){
        Result res = new Result();
        return res;
    }

    //根据时间和演出厅id查询演出计划
    @Override
    public Result selectScheduleByhallId(Integer id,String date){
        Result res = new Result();
        return res;
    }

    //删除演出计划
    @Override
    public Boolean deleteSchedule(Integer scheduleId){
        return true;
    }

    //修改演出计划
    @Override
    public Boolean modifySchedule(Schedule schedule){
        return true;
    }
}
