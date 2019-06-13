package com.coco.entity;

import java.util.List;

/**
 * @Classname Reeticket
 * @Description TODO
 * @Date 19-6-14 上午2:36
 * @Created by xns
 */
public class Reeticket {
    private Integer count;
    List<Reticket> ticket;

    public Reeticket(Integer count,List<Reticket> ticket){
        this.count=count;
        this.ticket=ticket;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Reticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Reticket> ticket) {
        this.ticket = ticket;
    }
}
