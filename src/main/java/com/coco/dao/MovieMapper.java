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

    //通过电影id删除
    int deleteBymovieId(Integer movieId);

    int insert(Movie record);

    //根据电影名称进行查询
    Movie selectBymovieTitle(String movieTitle);

    //根据电影id进行查询
    Movie selectBymovieId(Integer movieId);

    List<HalfMovie> selectpart();

    List<Movie> selectAll();

    int updateByPrimaryKey(Movie record);
}