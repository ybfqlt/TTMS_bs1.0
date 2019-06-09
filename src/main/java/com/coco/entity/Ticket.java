package com.coco.entity;

import java.io.Serializable;

public class Ticket implements Serializable {
    private Long ticketId;

    private Integer seatId;

    private Integer scheduleId;

    private Short ticketStatus;

    private static final long serialVersionUID = 1L;

    public Ticket(Integer seatId,Integer scheduleId,Short ticketStatus) {
        this.seatId = seatId;
        this.scheduleId=scheduleId;
        this.ticketStatus=ticketStatus;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Short getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(Short ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ticketId=").append(ticketId);
        sb.append(", seatId=").append(seatId);
        sb.append(", scheduleId=").append(scheduleId);
        sb.append(", ticketStatus=").append(ticketStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}