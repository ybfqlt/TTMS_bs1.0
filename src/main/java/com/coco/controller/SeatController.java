package com.coco.controller;

import com.coco.entity.Result;
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
@RequestMapping(value="/in/jingli")
public class SeatController {
    @Autowired
    private SeatmanageService seatmanageService;

    /**
    * @Description 根据演出厅的iid返回演出厅的座位信息
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/gethallseat",method = RequestMethod.POST)
    public Map<String,Object> GetSeats(@RequestBody Map<String,Integer> map){
        Map<String,Object> ma = new HashMap<>();
        Result res = seatmanageService.Gethallseat(map.get("hallId"));
        if(res.getJudge()==true){
            ma.put("seatState",true);
            ma.put("msg","获取座位成功");
            ma.put("data",res.getMes());
        }
        else{
            ma.put("seatState",false);
            ma.put("msg",res.getMes());
        }
        return ma;
    }

    /**
    * @Description 根据演出厅id初始化座位
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/inithallseat",method = RequestMethod.POST)
    public Map<String,Object> InitSeat(@RequestBody Map<String,Integer> map){
        Map<String,Object> ma = new HashMap<>();
        Boolean judge = seatmanageService.Inithallseat(map.get("hallId"));
        if(judge == true){
            ma.put("initState",true);
            ma.put("msg","初始化完成!!!");
        }
        else{
            ma.put("initState",false);
            ma.put("msg","演出厅不存在，初始化座位失败。");
        }
        return ma;
    }
}
