package com.coco.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Classname Reschedule
 * @Description TODO
 * @Date 19-6-13 上午11:46
 * @Created by xns
 */
public class Reschedule {
    private Integer scheduleId;

    private Integer hallId;

    private String hallName;

    private String movieTitle;

    private Timestamp scheduleStartTime;

    private Timestamp scheduleEndTime;

    private BigDecimal scheduleTicketPrice;

    public Reschedule(Integer scheduleId,Integer hallId,String hallName,String movieName,Timestamp scheduleStartTime,Timestamp scheduleEndTime,BigDecimal scheduleTicketPrice){
        this.scheduleId=scheduleId;
        this.hallId=hallId;
        this.hallName=hallName;
        this.movieTitle=movieName;
        this.scheduleStartTime=scheduleStartTime;
        this.scheduleEndTime=scheduleEndTime;
        this.scheduleTicketPrice=scheduleTicketPrice;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public Timestamp getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(Timestamp scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public Timestamp getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(Timestamp scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

    public BigDecimal getScheduleTicketPrice() {
        return scheduleTicketPrice;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setScheduleTicketPrice(BigDecimal scheduleTicketPrice) {
        this.scheduleTicketPrice = scheduleTicketPrice;
    }

}
