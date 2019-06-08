package com.coco.controller;

import com.coco.entity.Movie;
import com.coco.service.MoviemanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Classname MoviemanageController
 * @Description TODO
 * @Date 19-6-7 下午11:14
 * @Created by xns
 */
@RestController
@RequestMapping(value="/in/jingli")
public class MoviemanageController {

    @Autowired
    private MoviemanageService moviemanageService;

    /**
    * @Description 增加剧目
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/addmovie",method= RequestMethod.POST)
    public Map<String,Object> Addmovie(@RequestBody Movie movie){
        Map<String,Object> ma = new HashMap<>();
        Boolean judge = moviemanageService.addMovie(movie);
        if(judge == true){
            ma.put("addState",true);
            ma.put("msg","添加成功!!!");
        }
        else{
            ma.put("addState",false);
            ma.put("msg","此剧目已经存在!!!");
        }
        return ma;
    }

    /**
    * @Description 删除剧目
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="deletemovie",method=RequestMethod.POST)
    public Map<String,Object> Deletemovie(@RequestBody Map<String,String> map){
        Map<String,Object> ma = new HashMap<>();
        Boolean judge = moviemanageService.deleteMovie(map.get("movieTilte"));
        if(judge==false){
            ma.put("deleteState",false);
            ma.put("msg","删除剧目失败！此剧目不存在!");
        }
        else{
            ma.put("deleteState",true);
            ma.put("msg","删除剧目成功!!!");
        }
        return ma;
    }

    /**
    * @Description 修改剧目
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="modifymovie",method=RequestMethod.POST)
    public Map<String,Object> Modifymovie(@RequestBody Movie movie){
        Map<String,Object> ma = new HashMap<>();
        Boolean judge = moviemanageService.modifyMovie(movie);
        if(judge == false){
            ma.put("modifyState",false);
            ma.put("msg","此影片不存在!!!");
        }
        else{
            ma.put("modifyState",true);
            ma.put("msg","修改成功!!!");
        }
        return ma;
    }
}
