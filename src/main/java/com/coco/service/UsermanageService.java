package com.coco.service;

import com.coco.entity.Result;
import com.coco.entity.user;

import java.util.List;

/**
 * @Classname UsermanageService
 * @Description TODO
 * @Date 19-6-7 上午10:31
 * @Created by xns
 */
public interface UsermanageService {
    //添加一个经理
    Boolean addjUser(user uu);

    //删除某一个经理
    Boolean deletejUser(String name);

    //批量删除经理
    Result deletesomejUser(List<String> listnname);

    //修改经理的信息
    Boolean modifyjUser(user uu);

    //查看经理
    Result selectjUser();

    //查看所有普通用户
    Result selectUser();

    //根据用户名称返回用户id
    Integer getuserId(String name);

    //根据用户名称返回用户
    user getUser(String name);

    //获取某个用户已经注册的天数
    Integer getDay(String name);
}
