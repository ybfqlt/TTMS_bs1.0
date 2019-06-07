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
    Boolean addjUser(user uu);

    Boolean deletejUser(String name);

    Result deletesomejUser(List<String> listnname);

    Boolean modifyjUser(user uu);

    //查看经理
    Result selectjUser();
    //查看所有普通用户
    Result selectUser();
}
