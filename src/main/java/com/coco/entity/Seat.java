package com.coco.entity;

import java.io.Serializable;

public class Seat implements Serializable {
    private Integer seatId;

    private Integer hallId;

    private Integer seatRow;

    private Integer seatCol;

    private Short seatStatus;

    private static final long serialVersionUID = 1L;
    public Seat(Integer hallId,Integer seatRow,Integer seatCol,Short seatStatus){
        this.hallId=hallId;
        this.seatRow=seatRow;
        this.seatCol=seatCol;
        this.seatStatus=seatStatus;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
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

    public Short getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(Short seatStatus) {
        this.seatStatus = seatStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", seatId=").append(seatId);
        sb.append(", hallId=").append(hallId);
        sb.append(", seatRow=").append(seatRow);
        sb.append(", seatCol=").append(seatCol);
        sb.append(", seatStatus=").append(seatStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}