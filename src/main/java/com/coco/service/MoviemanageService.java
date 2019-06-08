package com.coco.service;

import com.coco.entity.Movie;

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
    Boolean deleteMovie(String name);

    //修改影片信息
    Boolean modifyMovie(Movie movie);


}
