package com.coco.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class Schedule implements Serializable {
    private Integer scheduleId;

    private Integer hallId;

    private Integer movieId;

    private Date scheduleStartTime;

    private Timestamp scheduleEndTime;

    private BigDecimal scheduleTicketPrice;

    private Short ticketinitStatus;

    private static final long serialVersionUID = 1L;

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

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Date getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(Date scheduleStartTime) {
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

    public void setScheduleTicketPrice(BigDecimal scheduleTicketPrice) {
        this.scheduleTicketPrice = scheduleTicketPrice;
    }

    public Short getTicketinitStatus() {
        return ticketinitStatus;
    }

    public void setTicketinitStatus(Short ticketinitStatus) {
        this.ticketinitStatus = ticketinitStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", scheduleId=").append(scheduleId);
        sb.append(", hallId=").append(hallId);
        sb.append(", movieId=").append(movieId);
        sb.append(", scheduleStartTime=").append(scheduleStartTime);
        sb.append(", scheduleEndTime=").append(scheduleEndTime);
        sb.append(", scheduleTicketPrice=").append(scheduleTicketPrice);
        sb.append(", ticketinitStatus=").append(ticketinitStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}