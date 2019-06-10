package com.coco.entity;

/**
 * @Classname Reorder
 * @Description TODO
 * @Date 19-6-10 下午4:12
 * @Created by xns
 */
public class Reorder {
    private Integer movieId;

    private Integer scheduleId;

    private Integer seatRow;

    public Reorder(Integer movieId, Integer scheduleId, Integer seatRow, Integer seatCol){
        this.movieId=movieId;
        this.scheduleId=scheduleId;
        this.seatRow=seatRow;
        this.seatCol=seatCol;
    }
    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(Integer seatRow) {
        this.seatRow = seatRow;
    }

    public Integer getSeatCol() {
        return seatCol;
    }

    public void setSeatCol(Integer seatCol) {
        this.seatCol = seatCol;
    }

    private Integer seatCol;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
}
