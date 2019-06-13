package com.coco.service.impl;

import com.coco.dao.HallMapper;
import com.coco.dao.MovieMapper;
import com.coco.dao.ScheduleMapper;
import com.coco.entity.*;
import com.coco.service.ScheduleService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.RectangularShape;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public Result addSchedule(Schedule schedule) throws ParseException{
        Result res = new Result();
        Movie movie = movieMapper.selectBymovieId(schedule.getMovieId());
        Hall hall = hallMapper.selectByPrimaryKey(schedule.getHallId());
        if(movie == null){
            res.setJudge(false);
            res.setMes("剧目不存在!!!");
        }
        if(hall == null){
            res.setJudge(false);
            res.setMes("演出厅不存在!!!");
        }


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar endtime = Calendar.getInstance();
        endtime.setTime(schedule.getScheduleStartTime());
        endtime.add(Calendar.MINUTE,movie.getMovieRuntime());
        Timestamp enddate = Timestamp.valueOf(df.format(endtime.getTime()));//string转换为Timestamp类型
        schedule.setScheduleEndTime(enddate);


        Date date=new Date();//获取系统时间
        if(date.getTime()>=((Date)endtime.getTime()).getTime()){
            res.setJudge(false);
            res.setMes("对不起，您添加的时间已经是过去时，请慎重!!!");
        }

        //留出入场时间
        Calendar beforestart = Calendar.getInstance();
        beforestart.setTime(schedule.getScheduleStartTime());
        beforestart.add(Calendar.MINUTE,-30);

        //加上退场时间
        Calendar afterend = Calendar.getInstance();
        afterend.setTime(schedule.getScheduleEndTime());
        afterend.add(Calendar.MINUTE,20);

        List<Schedule> schedules = scheduleMapper.selectByhallIdstarttime(schedule.getHallId(),df.format(beforestart.getTime()),df.format(afterend.getTime()));
        if(schedules.size()!=0){
            res.setJudge(false);
            res.setMes("对不起，在此时间段有其他计划，不能添加!!!");
        }
        else{
            List<Schedule> scheduless = scheduleMapper.selectByhallIdendtime(schedule.getHallId(),df.format(beforestart.getTime()),df.format(afterend.getTime()));
            if(scheduless.size()!=0){
                res.setJudge(false);
                res.setMes("对不起，在此时间段有其他计划，不能添加!!!");
            }
            else{
                schedule.setTicketinitStatus(Short.parseShort("0"));
                scheduleMapper.insert(schedule);
                res.setJudge(true);
                res.setMes("添加成功,演出票生成成功!!!");
            }
        }
        return res;
    }

    /**
    * @Description 根据剧目名称查询所有相关演出计划
    * @return com.coco.entity.Result
    *
    **/
    @Override
    public Result selectScheduleBymovieTitle(String movieTitle){
        Result res = new Result( );
        Movie movie = movieMapper.selectBymovieTitle(movieTitle);
        Date date = new Date();
        Timestamp ndate = new Timestamp(date.getTime());
        if(movie == null){
            res.setJudge(false);
            res.setMes("此剧目不存在!!!");
        }else{
            List<Schedule> schedules = scheduleMapper.selectBymovieId(movie.getMovieId());
            for(int i=0;i<schedules.size();i++){
                if(schedules.get(i).getScheduleEndTime().before(ndate)){
                    schedules.remove(i);
                }
            }
            if(schedules.size()==0){
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
        List<Reschedule> reschedules = new ArrayList<>();
        if(schedules == null){
            res.setJudge(false);
            res.setMes("暂时没有演出计划!!!");
        }
        else{
            for(int i=0;i<schedules.size();i++){
                Hall hall = hallMapper.selectByPrimaryKey(schedules.get(i).getHallId());
                Movie movie = movieMapper.selectBymovieId(schedules.get(i).getMovieId());
                Reschedule aa = new Reschedule(schedules.get(i).getScheduleId(),schedules.get(i).getHallId(),hall.getHallName(),movie.getMovieTitle(),schedules.get(i).getScheduleStartTime(),schedules.get(i).getScheduleEndTime(),schedules.get(i).getScheduleTicketPrice());
                reschedules.add(aa);
            }
            res.setJudge(true);
            res.setMes(reschedules);
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
        scheduleMapper.deleteByPrimaryKey(scheduleId);
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

    /**
    * @Description 根据scheduleId返回演出计划的信息
    * @return com.coco.entity.Schedule
    *
    **/
    @Override
    public Schedule GetscheduleByscheduleId(Integer scheduleId){
        Schedule schedule = scheduleMapper.selectByscheduleId(scheduleId);
        return schedule;
    }

    /**
    * @Description 根据演出厅id及演出开始时间返回演出计划
    * @return com.coco.entity.Schedule
    *
    **/
    @Override
    public Integer get(Integer hallId, Timestamp time){
        System.out.println(scheduleMapper.selectBystarttime(hallId,time));
        return scheduleMapper.selectBystarttime(hallId,time);
    }
}
