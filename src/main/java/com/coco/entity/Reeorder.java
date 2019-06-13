package com.coco.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Classname Reeorder
 * @Description TODO
 * @Date 19-6-14 上午2:39
 * @Created by xns
 */
public class Reeorder {
    private Integer id;
    private BigDecimal amount;
    private Timestamp time;
    private Reeticket ticket;

    public Reeorder(Integer id,BigDecimal amount,Timestamp time,Reeticket ticket){
        this.id=id;
        this.amount=amount;
        this.time=time;
        this.ticket=ticket;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Reeticket getTicket() {
        return ticket;
    }

    public void setTicket(Reeticket ticket) {
        this.ticket = ticket;
    }
}
