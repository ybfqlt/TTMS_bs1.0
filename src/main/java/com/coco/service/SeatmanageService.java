package com.coco.service;

import com.coco.entity.Result;


/**
 * @Classname SeatmanageService
 * @Description TODO
 * @Date 19-6-8 下午12:05
 * @Created by xns
 */
public interface SeatmanageService {
    //根据演出厅id返回演出厅的座位
    Result Gethallseat(Integer hallId);

    //根据演出厅id初始化演出厅的座位
    Boolean Inithallseat(Integer hallId);

}
