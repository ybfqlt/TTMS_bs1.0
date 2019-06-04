package com.coco.entity;

import java.io.Serializable;

public class Movie implements Serializable {
    private Integer movieId;

    private Double movieRating;

    private String movieGenres;

    private Integer movieRuntime;

    private String movieTitle;

    private String moviePoster;

    private String movieWriters;

    private String movieDirectors;

    private String movieActors;

    private String moviePlotSimple;

    private String movieCountry;

    private String movieAlsoKnownAs;

    private static final long serialVersionUID = 1L;

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

    public Integer getMovieRuntime() {
        return movieRuntime;
    }

    public void setMovieRuntime(Integer movieRuntime) {
        this.movieRuntime = movieRuntime;
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

    public String getMovieWriters() {
        return movieWriters;
    }

    public void setMovieWriters(String movieWriters) {
        this.movieWriters = movieWriters;
    }

    public String getMovieDirectors() {
        return movieDirectors;
    }

    public void setMovieDirectors(String movieDirectors) {
        this.movieDirectors = movieDirectors;
    }

    public String getMovieActors() {
        return movieActors;
    }

    public void setMovieActors(String movieActors) {
        this.movieActors = movieActors;
    }

    public String getMoviePlotSimple() {
        return moviePlotSimple;
    }

    public void setMoviePlotSimple(String moviePlotSimple) {
        this.moviePlotSimple = moviePlotSimple;
    }

    public String getMovieCountry() {
        return movieCountry;
    }

    public void setMovieCountry(String movieCountry) {
        this.movieCountry = movieCountry;
    }

    public String getMovieAlsoKnownAs() {
        return movieAlsoKnownAs;
    }

    public void setMovieAlsoKnownAs(String movieAlsoKnownAs) {
        this.movieAlsoKnownAs = movieAlsoKnownAs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", movieId=").append(movieId);
        sb.append(", movieRating=").append(movieRating);
        sb.append(", movieGenres=").append(movieGenres);
        sb.append(", movieRuntime=").append(movieRuntime);
        sb.append(", movieTitle=").append(movieTitle);
        sb.append(", moviePoster=").append(moviePoster);
        sb.append(", movieWriters=").append(movieWriters);
        sb.append(", movieDirectors=").append(movieDirectors);
        sb.append(", movieActors=").append(movieActors);
        sb.append(", moviePlotSimple=").append(moviePlotSimple);
        sb.append(", movieCountry=").append(movieCountry);
        sb.append(", movieAlsoKnownAs=").append(movieAlsoKnownAs);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}