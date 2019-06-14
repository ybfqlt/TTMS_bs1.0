package com.coco.service;

import com.coco.entity.Movie;
import com.coco.entity.Result;
import com.coco.entity.partmovie;

import java.util.List;

/**
 * @Classname MoviemanageService
 * @Description TODO
 * @Date 19-6-8 上午10:22
 * @Created by xns
 */
public interface MoviemanageService {
    //增加影片
    Boolean addMovie(Movie movie);

    //删除影片
    Boolean deleteMovie(Integer movieId);

    //修改影片信息
    Boolean modifyMovie(Movie movie);

    //根据影片id返回影片信息
    Movie getMovie(Integer movieId);

    //返回所有影片的部分片段
    List<partmovie> gethalfmovie();

    //根据字母进行用户的模糊查询
    Result getHumovie(String title);
}
