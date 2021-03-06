package com.coco.controller;

import com.coco.entity.Hall;
import com.coco.service.HallService;
import com.coco.service.SeatmanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname HallController
 * @Description hall manager
 * @Date 19-6-5 上午10:12
 * @Created by xns
 */
@RestController
@RequestMapping("/in/jingli")
public class HallController {
    @Autowired
    private HallService hallService;

    @Autowired
    private SeatmanageService seatmanageService;

    /**
    * @Description Return to all performance halls
    * @return java.util.List<com.coco.entity.Hall>
    *
    **/
    @RequestMapping(value="/showhall",method= RequestMethod.GET)
    public Map<String,Object> GetallHall(){
        Map ma = new HashMap();
        int a=0;
        List<Hall> halls = hallService.getAllHalls();
        if(halls == null){
            ma.put("count",0);
            ma.put("hallsSate",false);
            ma.put("msg","暂时还没有演出厅数据");
        }
        else{
            ma.put("count",halls.size());
            ma.put("hallsSate",false);
            ma.put("data",halls);
            ma.put("msg","success!!!");
        }
        return ma;
    }

    /**
    * @Description Query a certain performance hall by name
    * @return com.coco.entity.Result
    *
    **/
    @RequestMapping(value="/showonehall",method=RequestMethod.POST)
    public Map<String,Object> GetoneHall(@RequestBody Map<String,String> map){
        Hall hall = hallService.getoneHall(map.get("hallName"));
        Map<String,Object> ma = new HashMap<>();
        if(hall == null){
            ma.put("hallState",false);
            ma.put("msg","对不起，此演出厅不存在!");
        }
        else{
            ma.put("hallState",false);
            ma.put("data",hall);
            ma.put("msg","对不起，此演出厅不存在!");
        }
        return ma;
    }


    /**
    * @Description add one hall
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/addhall",method=RequestMethod.POST)
    public Map<String,Object> Addmoviehall(@RequestBody Hall hall){
        hall.setHallSeatCount(hall.getHallSeatCol()*hall.getHallSeatRow());
        Boolean judge = hallService.AddHall(hall);
        Map ma = new HashMap();
        if(judge==true) {
            Hall hal = hallService.GethallId(hall.getHallName());
            Boolean judge2 = seatmanageService.Inithallseat(hal.getHallId());//初始化座位
            if(judge2==false){
                ma.put("addState",true);
                ma.put("msg","添加成功，但是座位初始化失败");
            }
            else{
                ma.put("msg", "添加成功");
                ma.put("addState",true);
            }
        }
        else{
            ma.put("msg","对不起,您要添加的影厅已经存在");
        }
        return ma;
    }

    /**
    * @Description delete one movie
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/deletehall",method=RequestMethod.POST)
    public Map<String,Object> DeletemovieHall(@RequestBody Map<String,Integer> map){
        Boolean judge = hallService.DeleteHall(map.get("hallId"));
        Map ma = new HashMap();
        ma.put("deleteState",judge);
        if(judge==true) {
            ma.put("msg", "删除成功");
        }
        else{
            ma.put("msg","对不起,您要删除的影厅不存在");
        }
        return ma;
    }

    /**
    * @Description modify hall
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/modifyhall",method=RequestMethod.POST)
    public Map<String,Object> ModifymovieHall(@RequestBody Hall hall){
        Map ma = new HashMap();
        Boolean judge = hallService.ModifyHall(hall);
        ma.put("modifyState",judge);
        if(judge==false){
            ma.put("msg","修改失败，你要修改的名称和已经存在，请重新换一个名称!!!");
        }else{
            ma.put("msg","修改成功!!!");
        }
        return ma;
    }
}
