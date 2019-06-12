package com.coco.service;

import com.coco.entity.Hall;

import java.util.List;

/**
 * @Classname HallService
 * @Description TODO
 * @Date 19-6-5 上午10:12
 * @Created by xns
 */

public interface HallService {
    List<Hall> getAllHalls();

    Hall getoneHall(String hallName);

    Boolean AddHall(Hall hall);

    Boolean DeleteHall(Integer hallId);

    Boolean ModifyHall(Hall hall);

    //个人剧hallId获取演出厅信息
    Hall Gethall(Integer hallId);

    //根据名称获取演出厅
    Hall GethallId(String hallName);
}
