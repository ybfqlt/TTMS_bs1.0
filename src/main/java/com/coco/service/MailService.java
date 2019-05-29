package com.coco.service;

import com.coco.entity.Result;

/**
 * @Classname MailService
 * @Description TODO
 * @Date 19-5-29 下午6:14
 * @Created by ltt
 */
public interface MailService {

    public Result SendSecuritycode(String mail);
}
