package com.coco.service;

import com.coco.entity.HalfMovie;
import com.coco.entity.Movie;

import java.util.List;

/**
 * @Classname ShowhomepageService
 * @Description TODO
 * @Date 19-6-4 上午11:18
 * @Created by xns
 */
public interface ShowhomepageService {
    List<HalfMovie> showsomeMovie();

    Movie showoneMovie(String movieTitle);
}
