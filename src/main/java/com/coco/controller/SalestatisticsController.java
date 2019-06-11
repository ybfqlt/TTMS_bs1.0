package com.coco.controller;

import com.coco.entity.Restatistics;
import com.coco.entity.Result;
import com.coco.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname SalestatisticsController
 * @Description TODO
 * @Date 19-6-11 上午10:23
 * @Created by xns
 */
@RestController
@RequestMapping(value="/in")
public class SalestatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value="/tickethome",method= RequestMethod.GET)
    public Map<String,Object> getTickethome(){
        Map<String,Object> ma = new HashMap<>();
        Result res = statisticsService.returnStatistics();
        if(res.getJudge()==true){
            List<Restatistics> list = (List<Restatistics>)res.getMes();
            ma.put("movieCount",list.size());
            ma.put("result",list);
            ma.put("msg","查到了!!!");
        }
        else{
            ma.put("movieCount",0);
            ma.put("msg","没有查到!!!");
        }
        return ma;
    }
}
