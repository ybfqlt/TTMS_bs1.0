package com.coco.service.impl;

import com.coco.dao.MovieMapper;
import com.coco.entity.HalfMovie;
import com.coco.entity.Movie;
import com.coco.service.ShowhomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname Showhomepageimpl
 * @Description TODO
 * @Date 19-6-4 上午11:19
 * @Created by xns
 */
@Service("ShowhomepageService")
public class Showhomepageimpl implements ShowhomepageService {

    @Autowired
    private MovieMapper movieMapper;

    /**
    * @Description Query the id, title, poster of the movie in the database
    * @return java.util.List<com.coco.entity.HalfMovie>
    *
    **/
    @Override
    public List<HalfMovie> showsomeMovie(){
        List<HalfMovie> movies = movieMapper.selectpart();
        return movies;
    }

    /**
    * @Description Query information about a movie
    * @return com.coco.entity.Movie
    *
    **/
    @Override
    public Movie showoneMovie(String movieTitle){
        Movie movies = movieMapper.selectBymovieTitle(movieTitle);
        return movies;
    }
}
