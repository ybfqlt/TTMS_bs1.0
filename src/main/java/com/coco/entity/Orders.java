package com.coco.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Orders implements Serializable {
    private Integer ordersId;

    private Integer userId;

    private Long ticketId;

    private Timestamp ordersTime;

    private Short ordersType;

    public Orders(Integer userId,Long ticketId,Short ordersType){
        this.userId=userId;
        this.ticketId=ticketId;
        this.ordersType=ordersType;
    }

    private static final long serialVersionUID = 1L;

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Date getOrdersTime() {
        return ordersTime;
    }

    public void setOrdersTime(Timestamp ordersTime) {
        this.ordersTime = ordersTime;
    }

    public Short getOrdersType() {
        return ordersType;
    }

    public void setOrdersType(Short ordersType) {
        this.ordersType = ordersType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ordersId=").append(ordersId);
        sb.append(", userId=").append(userId);
        sb.append(", ticketId=").append(ticketId);
        sb.append(", ordersTime=").append(ordersTime);
        sb.append(", ordersType=").append(ordersType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}