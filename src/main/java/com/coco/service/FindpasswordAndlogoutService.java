package com.coco.service;

import com.coco.entity.user;

/**
 * @Classname FindpasswordAndlogoutService
 * @Description TODO
 * @Date 19-6-2 上午10:50
 * @Created by xns
 */

public interface FindpasswordAndlogoutService {
    Boolean findqq(String userQq);

    Boolean findname(String userName);

    String returnqq(String userName);

    Boolean changePasswordByqq(String userQq,String password);

    Boolean changePasswordByname(String userName,String password);
}
