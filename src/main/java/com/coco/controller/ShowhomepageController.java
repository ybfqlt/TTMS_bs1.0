package com.coco.controller;

import com.coco.entity.Movie;
import com.coco.service.ShowhomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname ShowhomepageController
 * @Description 显示movie
 * @Date 19-6-4 上午11:13
 * @Created by ltt
 */
@RestController
@RequestMapping(value="/in")
public class ShowhomepageController {

    @Autowired
    private ShowhomepageService showhomepageService;

    @RequestMapping(value="/showmovie",method= RequestMethod.GET)
    public List<Movie> Showmovie(){
        List<Movie> movies = showhomepageService.showsomeMovie();
        return movies;
    }
}
