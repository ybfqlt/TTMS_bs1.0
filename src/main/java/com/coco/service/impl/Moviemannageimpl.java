package com.coco.service.impl;

import com.coco.dao.MovieMapper;
import com.coco.dao.SalestatisticsMapper;
import com.coco.entity.*;
import com.coco.service.MoviemanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println(moo);
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
    public Boolean deleteMovie(Integer movieId){
        Movie mo = movieMapper.selectBymovieId(movieId);
        if(mo != null){
            movieMapper.deleteBymovieId(movieId);
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

    /**
    * @Description 根据影片id返回影片信息
    * @return com.coco.entity.Movie
    *
    **/
    @Override
    public Movie getMovie(Integer movieId){
        Movie movie = movieMapper.selectBymovieId(movieId);
        return movie;
    }

    /**
    * @Description 返回所有影片的部分片段
    * @return com.coco.entity.partmovie
    *
    **/
    @Override
    public List<partmovie> gethalfmovie(){
        List<partmovie> movies = new ArrayList<>();
        List<Movie> movie = movieMapper.selectAll();
        for(int i=0;i<movie.size();i++){
            partmovie mm = new partmovie(movie.get(i).getMovieId(),movie.get(i).getMovieRating(),movie.get(i).getMovieTitle(),movie.get(i).getMovieGenres(),movie.get(i).getMovieCountry(),movie.get(i).getMovieRuntime());
            movies.add(mm);
        }
        return movies;
    }

    /**
     * @Description 根据字母进行用户的模糊查询
     * @return java.util.List<com.coco.entity.user>
     *
     **/
    @Override
    public Result getHumovie(String title){
        Result res = new Result();
        List<partmovie> moviess = new ArrayList<>();
        List<Movie> movies = movieMapper.selectHuMovie(title);
        for(int i=0;i<movies.size();i++){
            partmovie mm = new partmovie(movies.get(i).getMovieId(),movies.get(i).getMovieRating(),movies.get(i).getMovieTitle(),movies.get(i).getMovieGenres(),movies.get(i).getMovieCountry(),movies.get(i).getMovieRuntime());
            moviess.add(mm);
        }
        if(movies.size()==0){
            res.setJudge(false);
            res.setMes("未查到数据!!!");
        }
        else{
            res.setJudge(true);
            res.setMes(moviess);
        }
        return res;
    }
}
