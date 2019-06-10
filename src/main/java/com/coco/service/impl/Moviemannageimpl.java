package com.coco.service.impl;

import com.coco.dao.MovieMapper;
import com.coco.dao.SalestatisticsMapper;
import com.coco.entity.Movie;
import com.coco.entity.Salestatistics;
import com.coco.service.MoviemanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Classname Moviemannageimpl
 * @Description TODO
 * @Date 19-6-8 上午10:23
 * @Created by xns
 */
@Service("MoviemanageService")
public class Moviemannageimpl implements MoviemanageService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private SalestatisticsMapper salestatisticsMapper;

    /**
    * @Description 增加剧目
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean addMovie(Movie movie){
        Movie mo = movieMapper.selectBymovieTitle(movie.getMovieTitle());
        if(mo==null){
            movieMapper.insert(movie);
            //对每一个新加入的剧目在票房表里进行插入
            Movie moo = movieMapper.selectBymovieTitle(movie.getMovieTitle());
            Salestatistics sa = new Salestatistics(moo.getMovieId(),Long.valueOf(0),BigDecimal.valueOf(0));
            salestatisticsMapper.insert(sa);
            return true;
        }
        else{
            return false;
        }
    }

    /**
    * @Description 删除剧目
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean deleteMovie(String title){
        Movie mo = movieMapper.selectBymovieTitle(title);
        if(mo != null){
            movieMapper.deleteBymovieTitle(title);
            return true;
        }
        else{
            return false;
        }
    }

    /**
    * @Description 修改剧目
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean modifyMovie(Movie movie){
        Movie mo = movieMapper.selectBymovieTitle(movie.getMovieTitle());
        if(mo == null){
            return false;
        }
        else{
            movieMapper.updateByPrimaryKey(movie);
            return true;
        }
    }
}