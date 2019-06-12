package com.coco.service.impl;

import com.coco.dao.HallMapper;
import com.coco.dao.SeatMapper;
import com.coco.dao.TicketMapper;
import com.coco.entity.*;
import com.coco.service.SeatmanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname Seatmanageimpl
 * @Description TODO
 * @Date 19-6-8 下午12:06
 * @Created by xns
 */
@Service("SeatmanageService")
public class Seatmanageimpl implements SeatmanageService {
    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private HallMapper hallMapper;

    @Autowired
    private TicketMapper ticketMapper;

    /**
    * @Description 根据演出厅id返回演出厅的座位
    * @return com.coco.entity.Result
    *
    **/
    @Override
    public Result Gethallseat(Integer hallId, Schedule schedule){
        Result res = new Result();
        Map<String,Integer> a = new LinkedHashMap<>();
        List<Seat> seats = seatMapper.selectByHallId(hallId);
        for(int i=0;i<seats.size();i++){
            if(seats.get(i).getSeatStatus()==0){
                a.put("state",0);
            }else {
                Ticket ticket = ticketMapper.selectByseatIdsid(seats.get(i).getSeatId(), schedule.getScheduleId());
                if (ticket.getTicketStatus() == 0) {
                    a.put("state",2);
                } else {
                    a.put("state",1);
                }
            }
        }
        if(seats == null){
           res = new Result(false,"此演出厅未进行初始化，暂时没有座位信息!!!");
        }
        else{
           res = new Result(true,a);
        }
        return res;
    }

    /**
    * @Description 根据演出厅id初始化演出厅的座位
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean Inithallseat(Integer hallId){
        Hall hall = hallMapper.selectByPrimaryKey(hallId);
        if(hall==null){
            return false;
        }else{
            for(int i=1;i<=hall.getHallSeatRow();i++){
                for(int j=1;j<=hall.getHallSeatCol();j++){
                    Seat seat = new Seat(hall.getHallId(),i,j,Short.parseShort("1"));
                    seatMapper.insert(seat);
                }
            }
            return true;
        }
    }

    /**
    * @Description 根据演出厅id加载管理员座位页面
    * @return com.coco.entity.Result
    *
    **/
    @Override
    public Result getjingliseat(Integer hallId){
        Result res = new Result();
        Map<String,Integer> a = new LinkedHashMap<>();
        List<Seat> seats = seatMapper.selectByHallId(hallId);
        for(int i=0;i<seats.size();i++){
            if(seats.get(i).getSeatStatus()==0){
                a.put("state",0);
            }else {
                a.put("state",1);
            }
        }
        if(seats == null){
            res = new Result(false,"此演出厅未进行初始化，暂时没有座位信息!!!");
        }
        else{
            res = new Result(true,a);
        }
        return res;
    }

    /**
    * @Description 根据演出厅id更新座位的好坏和前端的字符串
    * @return java.lang.Boolean
    *
    **/
    /*@Override
    public Boolean updateseatByid(Integer hallId,String seat){
        List<Hall> seats
    }*/
}
