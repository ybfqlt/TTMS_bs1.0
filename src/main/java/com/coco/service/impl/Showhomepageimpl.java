package com.coco.service.impl;

import com.coco.dao.MovieMapper;
import com.coco.entity.Movie;
import com.coco.service.ShowhomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname Showhomepageimpl
 * @Description TODO
 * @Date 19-6-4 上午11:19
 * @Created by ltt
 */
@Service("ShowhomepageService")
public class Showhomepageimpl implements ShowhomepageService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<Movie> showsomeMovie(){
        List<Movie> movies = movieMapper.selectAll();
        return movies;
    }
}
