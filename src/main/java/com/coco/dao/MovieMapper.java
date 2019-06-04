package com.coco.dao;

import com.coco.entity.HalfMovie;
import com.coco.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MovieMapper {
    int deleteBymovieTitle(String movieTitle);

    int insert(Movie record);

    Movie selectBymovieTitle(String movieTitle);


    List<HalfMovie> selectpart();

    List<Movie> selectAll();

    int updateByPrimaryKey(Movie record);
}