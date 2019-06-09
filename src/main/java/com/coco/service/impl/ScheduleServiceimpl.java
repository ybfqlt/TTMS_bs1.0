package com.coco.service.impl;

import com.coco.dao.HallMapper;
import com.coco.dao.MovieMapper;
import com.coco.dao.ScheduleMapper;
import com.coco.entity.Hall;
import com.coco.entity.Movie;
import com.coco.entity.Result;
import com.coco.entity.Schedule;
import com.coco.service.ScheduleService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    @Autowired
    private HallMapper hallMapper;

    /**
    * @Description 添加演出计划
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean addSchedule(Schedule schedule){
        Movie movie = movieMapper.selectBymovieId(schedule.getMovieId());
        Hall hall = hallMapper.selectByPrimaryKey(schedule.getHallId());
        if(movie == null || hall == null){
            return false;
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar endtime = Calendar.getInstance();
        endtime.setTime(schedule.getScheduleStartTime());
        endtime.add(Calendar.MINUTE,movie.getMovieRuntime());
        Timestamp enddate = Timestamp.valueOf(df.format(endtime.getTime()));//string转换为Timestamp类型
        schedule.setScheduleEndTime(enddate);


        Date date=new Date();//获取系统时间
        if(date.getTime()>=((Date)endtime.getTime()).getTime()){
            return false;
        }

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
        //自动生成演出票
        //等待写
    }

    /**
    * @Description 根据剧目名称查询所有相关演出计划
    * @return com.coco.entity.Result
    *
    **/
    @Override
    public Result selectScheduleBymovieId(String movieTitle){
        Result res = new Result( );
        Movie movie = movieMapper.selectBymovieTitle(movieTitle);
        if(movie == null){
            res.setJudge(false);
            res.setMes("此剧目不存在!!!");
        }else{
            List<Schedule> schedules = scheduleMapper.selectBymovieId(movie.getMovieId());
            if(schedules == null){
                res.setJudge(false);
                res.setMes("此剧目暂时没有相关演出计划!!!");
            }
            else{
                res.setJudge(true);
                res.setMes(schedules);
            }
        }
        return res;
    }


    /**
    * @Description 查询所有演出计划
    * @return com.coco.entity.Result
    *
    **/
    @Override
    public Result selectAllSchedule(){
        Result res = new Result();
        List<Schedule> schedules = scheduleMapper.selectAll();
        if(schedules == null){
            res.setJudge(false);
            res.setMes("暂时没有演出计划!!!");
        }
        else{
            res.setJudge(true);
            res.setMes(schedules);
        }
        return res;
    }

    /**
    * @Description 根据日期和剧目名称查询演出计划
    * @return com.coco.entity.Result
    *
    **/
    @Override
    public Result selectScheduleBydatetime(String date,String movieTitle){
        Result res = new Result();
        Movie movie = movieMapper.selectBymovieTitle(movieTitle);
        if(movie == null){
            res.setJudge(false);
            res.setMes("此剧目不存在!!!");
        }
        String starttime = date+" 00:00:00";
        String endtime=null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Calendar end = Calendar.getInstance();
            end.setTime(df.parse(starttime));
            end.add(Calendar.DATE,1);
            endtime = df.format(end.getTime());
        }catch(ParseException e){
            e.printStackTrace();
        }
        List<Schedule> schedules = scheduleMapper.selectBymovieIdtime(movie.getMovieId(),starttime,endtime);
        if(schedules == null){
            res.setJudge(false);
            res.setMes("暂时没有几年的演出计划!!!");
        }
        else{
            res.setJudge(true);
            res.setMes(schedules);
        }
        return res;
    }

    /**
    * @Description 根据时间和演出厅id查询演出计划
    * @return com.coco.entity.Result
    *
    **/
    @Override
    public Result selectScheduleByhallId(Integer id,String date){
        Result res = new Result();
        return res;
    }

    /**
    * @Description 删除演出计划
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean deleteSchedule(Integer scheduleId){
        return true;
    }

    /**
    * @Description 修改演出计划
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean modifySchedule(Schedule schedule){
        Schedule sch = scheduleMapper.selectByscheduleId(schedule.getScheduleId());
        if(sch == null){
            return false;
        }
        else{
            scheduleMapper.updateByScheduleId(schedule);
        }
        return true;
    }
}
