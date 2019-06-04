package com.coco.service;

import com.coco.entity.Result;

/**
 * @Classname MailService
 * @Description TODO
 * @Date 19-5-29 下午6:14
 * @Created by xns
 */
public interface MailService {

    Result SendSecuritycode(String mail);

    Boolean Securitycodeandname(String name);
}
