package com.coco.entity;

/**
 * @Classname partmovie
 * @Description TODO
 * @Date 19-6-12 下午3:01
 * @Created by xns
 */
public class partmovie {
    private Integer movieId;
    private Double movieRating;
    private String movieGenres;
    private String movieTitle;
    private String movieCountry;
    private Integer movieRuntime;

    public partmovie(Integer movieId,Double movieRating,String movieTitle,String movieGenres,String movieCountry,Integer movieRuntime){
        this.movieCountry=movieCountry;
        this.movieGenres=movieGenres;
        this.movieId=movieId;
        this.movieRuntime=movieRuntime;
        this.movieTitle=movieTitle;
        this.movieRating=movieRating;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Double getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(Double movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(String movieGenres) {
        this.movieGenres = movieGenres;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieCountry() {
        return movieCountry;
    }

    public void setMovieCountry(String movieCountry) {
        this.movieCountry = movieCountry;
    }

    public Integer getMovieRuntime() {
        return movieRuntime;
    }

    public void setMovieRuntime(Integer movieRuntime) {
        this.movieRuntime = movieRuntime;
    }
}
