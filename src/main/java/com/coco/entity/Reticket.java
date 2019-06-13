package com.coco.entity;

import java.util.concurrent.RecursiveAction;

/**
 * @Classname Reticket
 * @Description TODO
 * @Date 19-6-14 上午2:34
 * @Created by xns
 */
public class Reticket {
    private Long id;
    public Reticket(Long id){
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
