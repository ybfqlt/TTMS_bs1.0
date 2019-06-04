package com.coco.dao;

import com.coco.entity.Movie;
import java.util.List;

public interface MovieMapper {
    int deleteBymovieTitle(String movieTitle);

    int insert(Movie record);

    Movie selectBymovieTitle(String movieTitle);

    List<Movie> selectAll();

    int updateByPrimaryKey(Movie record);
}