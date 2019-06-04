package com.coco.controller;

import com.coco.entity.HalfMovie;
import com.coco.entity.Movie;
import com.coco.service.ShowhomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Classname ShowhomepageController
 * @Description 显示movie
 * @Date 19-6-4 上午11:13
 * @Created by xns
 */
@RestController
@RequestMapping(value="/in")
public class ShowhomepageController {

    @Autowired
    private ShowhomepageService showhomepageService;

    /**
    * @Description Return to the movie to be displayed on the home page
    * @return java.util.List<com.coco.entity.HalfMovie>
    *
    **/
    @RequestMapping(value="/showmovie",method= RequestMethod.GET)
    public List<HalfMovie> Showmovie(){
        List<HalfMovie> movies = showhomepageService.showsomeMovie();
        return movies;
    }

    /**
    * @Description Display all information about a movie
    * @return com.coco.entity.Movie
    *
    **/
    @RequestMapping(value= "/showonemovie",method=RequestMethod.POST)
    public Movie Showonemovie(@RequestBody Map<String ,String> map){
        Movie movies = showhomepageService.showoneMovie(map.get("movieTitle"));
        return movies;
    }
}
