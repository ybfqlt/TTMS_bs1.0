package com.coco.controller;

import com.coco.entity.Reschedule;
import com.coco.entity.Result;
import com.coco.entity.Schedule;
import com.coco.service.ScheduleService;
import com.coco.service.SeatmanageService;
import com.coco.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname ScheduleController
 * @Description TODO
 * @Date 19-6-8 下午4:09
 * @Created by xns
 */
@RestController
@RequestMapping(value="/in/jingli")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SeatmanageService seatmanageService;

    /**
    * @Description 添加演出计划
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/addschedule",method=RequestMethod.POST)
    public Map<String,Object> Addschedule(@RequestBody Map<String,Object> map){
        Schedule schedule = new Schedule((Integer)map.get("hallId"),(Integer)map.get("movieId"), Timestamp.valueOf((String)map.get("scheduleStartTime")),BigDecimal.valueOf((Double)map.get("scheduleTicketPrice")));
        Map<String,Object> ma = new HashMap<>();
        Result res = null;
        try {
            res = scheduleService.addSchedule(schedule);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(res.getJudge() == true) {
            int a = scheduleService.get(schedule.getHallId(),schedule.getScheduleStartTime());
            schedule.setScheduleId(a);
            Result ress = ticketService.ProduceticketByschedule(schedule);//根据添加的演出计划自动生成对应的票
            if (ress.getJudge() == true) {
                ma.put("addState", true);
                ma.put("msg", res.getMes());
            }
        }
        else{
            ma.put("addState",false);
            ma.put("msg",res.getMes());
        }
        return ma;
    }

    /**
    * @Description 根据剧目名称查询所有相关演出计划
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/selectBymovieTitle",method=RequestMethod.POST)
    public Map<String,Object> SelectBymovieId(@RequestBody Map<String,String> map){
        Map<String,Object> ma = new HashMap<>();
        Result res = scheduleService.selectScheduleBymovieTitle(map.get("movieTitle"));
        if(res.getJudge()==true){
            ma.put("count",((List<Schedule>)res.getMes()).size());
            ma.put("State",true);
            ma.put("data",res.getMes());
            ma.put("msg","查询到了!!!");
        }
        else{
            ma.put("count",0);
            ma.put("State",false);
            ma.put("msg",res.getMes());
        }
        return ma;
    }

    /**
    * @Description 查询所有演出计划
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/selectall",method=RequestMethod.GET)
    public Map<String,Object> SelectAll(){
        Map<String,Object> ma = new HashMap<>();
        Result res = scheduleService.selectAllSchedule();
        if(res.getJudge()==true){
            ma.put("count",((List<Reschedule>)res.getMes()).size());
            ma.put("selectallState",true);
            ma.put("data",res.getMes());
            ma.put("msg","查询到了!!!");
        }
        else{
            ma.put("selectallState",false);
            ma.put("msg",res.getMes());
        }
        return ma;
    }

    /**
    * @Description 根据日期和剧目名称查询演出计划
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/selectbydateandmovietitle",method=RequestMethod.POST)
    public Map<String,Object> SelectScheduleBydatemovietitle(@RequestBody Map<String,String> map){
        Map<String,Object> ma = new HashMap<>();
        Result res = scheduleService.selectScheduleBydatetime(map.get("date"),map.get("movieTitle"));
        if(res.getJudge()==true){
            ma.put("selectallState",true);
            ma.put("data",res.getMes());
            ma.put("msg","查询到了!!!");
        }
        else{
            ma.put("selectallState",false);
            ma.put("msg",res.getMes());
        }
        return ma;
    }

    /**
    * @Description 删除演出计划
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/deleteschedule",method = RequestMethod.POST)
    public Map<String,Object> DeleteSchedule(@RequestBody Map<String,Integer> map){
        Map<String,Object> ma=new HashMap<>();
        Result res = ticketService.DeletescheduleByscheduleId(map.get("scheduleId"));
        if(res.getJudge()==true){
            scheduleService.deleteSchedule(map.get("scheduleId"));
            ma.put("deleteState",true);
            ma.put("msg","删除演出计划成功，其对应的票也已经删除!!!");
        }
        else{
            ma.put("deleteState",false);
            ma.put("msg",res.getMes());
        }
        return ma;
    }
    /**
    * @Description 修改演出计划
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/modifyschedule",method = RequestMethod.POST)
    public Map<String,Object> Modifyschedule(@RequestBody Schedule schedule){
        Map<String,Object> ma = new HashMap<>();
        Boolean judge = scheduleService.modifySchedule(schedule);
        if(judge == true){
            ma.put("modifyState",true);
            ma.put("msg","修改成功");
        }
        else{
            ma.put("modifyState",false);
            ma.put("msg","此计划不存在，修改失败!!!");
        }
        return ma;
    }


    @RequestMapping(value="/selectsao",method = RequestMethod.POST)
    public Map<String,Object> Saoselect(@RequestBody Map<String,Integer> map){
        Map<String,Object> ma = new HashMap<>();
        Result res = scheduleService.selectByhallIdormovieId(map.get("hallId"),map.get("movieId"));
        if(res.getJudge() == true){
            ma.put("State",true);
            ma.put("data",res.getMes());
            ma.put("msg","查到了");
        }
        else{
            ma.put("State",false);
            ma.put("msg",res.getMes());
        }
        return ma;
    }
}
