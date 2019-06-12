package com.coco.dao;

import com.coco.entity.Hall;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HallMapper {
    int deleteByhallName(String hallName);

    int insert(Hall record);

    Hall selectByPrimaryKey(Integer hallId);

    List<Hall> selectAll();

    Hall selectByhallName(String hallName);

    int updateByPrimaryKey(Hall record);

}