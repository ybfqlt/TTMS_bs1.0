package com.coco.dao;

import com.coco.entity.user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface userMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(user record);

    user selectByuserName(String userName);

    List<user> selectAll();

    int updateByPrimaryKey(user record);
}