package com.coco.dao;

import com.coco.entity.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.xml.registry.infomodel.User;
import java.util.List;

@Mapper
@Repository
public interface userMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(user record);

    //添加经理
    int insertj(user record);

    user selectByuserName(String userName);

    user selectByuserQq(String userQq);

    List<user> selectAll();

    //返回所有经理的信息
    List<user> selectjAll();

    int updatepasswordByQq(user record);

    int updatepassworduserName(user record);

    int updateByuserId(user uu);

    //模糊查询用户
    List<user>  gethuuser(@Param("username")String username);
}