package com.coco.controller;

import com.coco.entity.Movie;
import com.coco.entity.partmovie;
import com.coco.service.MoviemanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
        /*Movie movie = new Movie(Double.parseDouble(map.get("movieRating")),map.get("movieGenres"), map.get("moviePlotSimple"), Integer.parseInt(map.get("movieRuntime")), map.get("movieTitle"), map.get("moviePoster"), map.get("movieWriters"), map.get("movieDirectors"), map.get("movieActors"), map.get("movieCountry"), map.get("movieAlsoKnownAs"));*/
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
    public Map<String,Object> Deletemovie(@RequestBody Map<String,Integer> map){
        Map<String,Object> ma = new HashMap<>();
        Boolean judge = moviemanageService.deleteMovie(map.get("movieId"));
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
    /**
    * @Description
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="selectmovie",method=RequestMethod.GET)
    public Map<String,Object> Selectmovie(){
        List<partmovie> movies = moviemanageService.gethalfmovie();
        Map<String,Object> ma = new HashMap<>();
        if(movies ==null){
            ma.put("count",0);
            ma.put("state",false);
            ma.put("msg","暂时无数据");
        }
        else{
            ma.put("count",movies.size());
            ma.put("state",true);
            ma.put("data",movies);
            ma.put("msg","查到了!!!");
        }
        return ma;
    }
}
