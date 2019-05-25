package com.coco.service.impl;

import com.coco.dao.userMapper;
import com.coco.entity.user;
import com.coco.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.registry.infomodel.User;

/**
 * @Classname LoginServiceimpl
 * @Description TODO
 * @Date 19-5-24 下午5:21
 * @Created by ltt
 */

@Service("LoginService")
public class LoginServiceimpl implements LoginService {

    @Autowired
    private userMapper usermapper;


    /**
    * @Description 验证登录用户名和密码
    * @return boolean
    *
    **/
    @Override
    public boolean judgelogin(String name,String password){
        user userr = usermapper.selectByuserName(name);
        if(userr == null){
            return false;
        }else{
            if(userr.getUserPassword().equals(password)){
                return true;
            }else{
                return false;
            }

        }
    }

    /**
    * @Description 查询是否注册过，若未注册过则注册，将其插入数据库
    * @return boolean
    *
    **/
    @Override
    public boolean judgeregister(user users){
        user useru = usermapper.selectByuserName(users.getUserName());
        if(useru==null){
            usermapper.insert(users);
            return true;
        }
        else{
            return false;
        }
    }
}
