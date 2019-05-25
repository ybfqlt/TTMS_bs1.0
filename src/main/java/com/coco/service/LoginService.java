package com.coco.service;

import com.coco.entity.user;
import org.springframework.stereotype.Service;

/**
 * @Classname LoginService
 * @Description TODO
 * @Date 19-5-24 下午5:21
 * @Created by ltt
 */


public interface LoginService {
    boolean judgelogin(String name,String password);

    boolean judgeregister(user users);
}
