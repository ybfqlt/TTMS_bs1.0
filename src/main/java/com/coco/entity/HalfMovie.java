package com.coco.entity;

/**
 * @Classname HalfMovie
 * @Description TODO
 * @Date 19-6-4 下午8:04
 * @Created by xns
 */
public class HalfMovie {
    private Integer movieId;
    private String movieTitle;
    private String moviePoster;


    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }
}
