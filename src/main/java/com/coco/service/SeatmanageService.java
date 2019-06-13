package com.coco.service;

import com.coco.entity.Result;
import com.coco.entity.Schedule;


/**
 * @Classname SeatmanageService
 * @Description TODO
 * @Date 19-6-8 下午12:05
 * @Created by xns
 */
public interface SeatmanageService {
    //根据演出厅id和演出计划返回演出厅的座位
    Result Gethallseat(Integer hallId,Schedule schedule);

    //根据演出厅id初始化演出厅的座位
    Boolean Inithallseat(Integer hallId);

    //根据演出厅id加载管理员座位页面
    Result getjingliseat(Integer hallId);

    //根据演出厅id更新座位的好坏
    Boolean Updatehallseat(Integer hallId,String seat);
}
