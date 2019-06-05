package com.coco.entity;

import java.io.Serializable;

public class Hall implements Serializable {
    private Integer hallId;

    private String hallName;

    private Integer hallSeatCount;

    private Integer hallSeatRow;

    private Integer hallSeatCol;

    private Short hallStatus;

    private static final long serialVersionUID = 1L;

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Integer getHallSeatCount() {
        return hallSeatCount;
    }

    public void setHallSeatCount(Integer hallSeatCount) {
        this.hallSeatCount = hallSeatCount;
    }

    public Integer getHallSeatRow() {
        return hallSeatRow;
    }

    public void setHallSeatRow(Integer hallSeatRow) {
        this.hallSeatRow = hallSeatRow;
    }

    public Integer getHallSeatCol() {
        return hallSeatCol;
    }

    public void setHallSeatCol(Integer hallSeatCol) {
        this.hallSeatCol = hallSeatCol;
    }

    public Short getHallStatus() {
        return hallStatus;
    }

    public void setHallStatus(Short hallStatus) {
        this.hallStatus = hallStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hallId=").append(hallId);
        sb.append(", hallName=").append(hallName);
        sb.append(", hallSeatCount=").append(hallSeatCount);
        sb.append(", hallSeatRow=").append(hallSeatRow);
        sb.append(", hallSeatCol=").append(hallSeatCol);
        sb.append(", hallStatus=").append(hallStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}