package com.coco.service.impl;

import com.coco.dao.HallMapper;
import com.coco.entity.Hall;
import com.coco.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname HallServiceimpl
 * @Description TODO
 * @Date 19-6-5 上午10:12
 * @Created by xns
 */
@Service("HallService")
public class HallServiceimpl implements HallService {

    @Autowired
    private HallMapper hallMapper;

    /**
    * @Description Get all the performance halls
    * @return java.util.List<com.coco.entity.Hall>
    *
    **/
    @Override
    public List<Hall> getAllHalls(){
        List<Hall> halls = hallMapper.selectAll();
        return halls;
    }

    /**
    * @Description Query a certain performance hall by name
    * @return com.coco.entity.Hall
    *
    **/
    @Override
    public Hall getoneHall(String hallName){
        Hall hall = hallMapper.selectByhallName(hallName);
        return hall;
    }

    /**
    * @Description Add a performance hall
    * @return java.lang.Boolean
    *
    **/
    public Boolean AddHall(Hall hall){
        Hall halll = hallMapper.selectByhallName(hall.getHallName());
        if(halll==null){
            hall.setHallStatus((short) 0);
            int a=hallMapper.insert(hall);
        }
        else{
           return false;
        }
        return true;
    }


    /**
    * @Description delete one movie
    * @return java.lang.Boolean
    *
    **/
    public Boolean DeleteHall(String hallName){
        Hall hall = hallMapper.selectByhallName(hallName);
        if(hall==null){
            return false;
        }
        else{
            int a = hallMapper.deleteByhallName(hallName);
        }
        return true;
    }

    /**
    * @Description Modify the performance hall
    * @return java.lang.Boolean
    *
    **/
    public Boolean ModifyHall(Hall hall){
        Hall halll = hallMapper.selectByPrimaryKey(hall.getHallId());
        if(halll==null){
            return false;
        }else{
            if(hall.getHallName().equals(halll.getHallName())){
                hallMapper.updateByPrimaryKey(hall);
                return true;
            }
            else{
                if(hallMapper.selectByPrimaryKey(hall.getHallId())==null){
                    hallMapper.updateByPrimaryKey(hall);
                    return true;
                }else{
                    return false;
                }
            }
        }
    }
}
