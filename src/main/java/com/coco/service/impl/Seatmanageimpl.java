package com.coco.service.impl;

import com.coco.dao.HallMapper;
import com.coco.dao.SeatMapper;
import com.coco.entity.Hall;
import com.coco.entity.Result;
import com.coco.entity.Seat;
import com.coco.service.SeatmanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
    * @Description 根据演出厅id返回演出厅的座位
    * @return com.coco.entity.Result
    *
    **/
    @Override
    public Result Gethallseat(Integer hallId){
        Result res;
        List<Seat> seats = seatMapper.selectByHallId(hallId);
        if(seats == null){
           res = new Result(false,"次演出厅未进行初始化，暂时没有座位信息!!!");
        }
        else{
           res = new Result(true,seats);
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
            hall.setHallStatus(Short.valueOf("1"));
            return true;
        }
    }

}
