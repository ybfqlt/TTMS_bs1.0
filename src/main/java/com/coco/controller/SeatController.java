package com.coco.controller;

import com.coco.entity.Hall;
import com.coco.entity.Movie;
import com.coco.entity.Schedule;
import com.coco.service.HallService;
import com.coco.service.MoviemanageService;
import com.coco.service.ScheduleService;
import com.coco.service.SeatmanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname SeatController
 * @Description TODO
 * @Date 19-6-8 下午12:05
 * @Created by xns
 */
@RestController
@RequestMapping(value="/in")
public class SeatController {
    @Autowired
    private SeatmanageService seatmanageService;

    @Autowired
    private MoviemanageService moviemanageService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private HallService hallService;

    /**
    * @Description 根据演出厅id更新座位
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/jingli/updateseat",method = RequestMethod.POST)
    public Map<String,Object> InitSeat(@RequestBody Map<String,Object> map){
        Map<String,Object> ma = new HashMap<>();
        Boolean judge = seatmanageService.Updatehallseat((Integer)map.get("hallId"),(String)map.get("State"));
        if(judge == true){
            ma.put("state",true);
            ma.put("msg","更新完成!!!");
        }
        else{
            ma.put("state",false);
            ma.put("msg","更新座位失败,无座位可更新!!!");
        }
        return ma;
    }


    /**
    * @Description 加载座位用户要看到的页面
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/loadseat",method=RequestMethod.POST)
    public Map<String,Object> Loadinguserseat(@RequestBody Map<String,Integer> map){
        Map<String,Object> ma =new HashMap<>();
        Schedule schedule = scheduleService.GetscheduleByscheduleId(map.get("scheduleId"));
        Movie movie = moviemanageService.getMovie(schedule.getMovieId());
        ma.put("moviePoster",movie.getMoviePoster());
        ma.put("movieTitle",movie.getMovieTitle());
        ma.put("movieType",movie.getMovieGenres());
        ma.put("movieRuntime",movie.getMovieRuntime());
        Hall hall = hallService.Gethall(schedule.getHallId());
        ma.put("hallName",hall.getHallName());
        ma.put("hallstarttime",schedule.getScheduleStartTime());
        ma.put("rowCount",hall.getHallSeatRow());
        ma.put("colCount",hall.getHallSeatCol());
        ma.put("seat",(seatmanageService.Gethallseat(hall.getHallId(),schedule)).getMes());
        return ma;
    }

    /**
     * @Description 加载座经理要看到的页面
     * @return java.util.Map<java.lang.String,java.lang.Object>
     *
     **/
    @RequestMapping(value="/jingli/loadjseat",method=RequestMethod.POST)
    public Map<String,Object> Loadingmanageseat(@RequestBody Map<String,Integer> map){
        Map<String,Object> ma =new HashMap<>();
        Hall hall = hallService.Gethall(map.get("hallId"));
        ma.put("hallName",hall.getHallName());
        ma.put("rowCount",hall.getHallSeatRow());
        ma.put("colCount",hall.getHallSeatCol());
        ma.put("seat",(seatmanageService.getjingliseat(map.get("hallId")).getMes()));
        return ma;
    }
}
