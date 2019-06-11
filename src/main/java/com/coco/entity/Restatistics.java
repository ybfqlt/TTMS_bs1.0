package com.coco.entity;

import java.math.BigDecimal;

/**
 * @Classname Restatistics
 * @Description TODO
 * @Date 19-6-11 上午10:04
 * @Created by xns
 */
public class Restatistics {
    private String name;
    private Long ticketValue;
    private BigDecimal saleValue;

    public Restatistics(String name,Long ticketValue,BigDecimal saleValue){
        this.name=name;
        this.ticketValue=ticketValue;
        this.saleValue=saleValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTicketValue() {
        return ticketValue;
    }

    public void setTicketValue(Long ticketValue) {
        this.ticketValue = ticketValue;
    }

    public BigDecimal getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(BigDecimal saleValue) {
        this.saleValue = saleValue;
    }
}
