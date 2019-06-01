package com.coco.service;

import com.coco.entity.Result;
import com.coco.entity.user;


/**
 * @Classname LoginService
 * @Description TODO
 * @Date 19-5-24 下午5:21
 * @Created by ltt
 */


public interface LoginService {
    Result judgelogin(String name, String password);

    Result securityqq(String qq);

    Boolean registeruser(user uu);
}
