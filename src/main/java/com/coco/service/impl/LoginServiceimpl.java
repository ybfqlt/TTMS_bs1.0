package com.coco.service.impl;

import com.coco.dao.userMapper;
import com.coco.entity.Result;
import com.coco.entity.user;
import com.coco.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Classname LoginServiceimpl
 * @Description TODO
 * @Date 19-5-24 下午5:21
 * @Created by ltt
 */

@Service("LoginService")
@Transactional
public class LoginServiceimpl implements LoginService {

    @Autowired
    private userMapper usermapper;


    /**
    * @Description 验证登录用户名和密码
    * @return boolean
    *
    **/
    @Override
    public Result judgelogin(String name,String password){
        user userr = usermapper.selectByuserName(name);
        Result result = new Result();
        if(userr == null){
            result.setMessage("用户名不存在");
        }else{
            if(userr.getUserPassword().equals(password)){
                result.setJudge(true);
                result.setMessage(userr.getUserType());
            }else{
                result.setJudge(false);
                result.setMessage("密码错误");
            }
        }
        return result;
    }

    /**
    * @Description 注册第一步，验证qq号是否存在，如果存在返回false,否则返回true
    * @return boolean
    *
    **/
    @Override
    public Result securityqq(String qq){
        user useru = usermapper.selectByuserQq(qq);
        Result result = new Result();
        if(useru==null){
            result.setJudge(true);
        }
        else{
            result.setJudge(false);
        }
        return result;
    }

    /**
    * @Description insert user
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean registeruser(user uu){
        int a = usermapper.insert(uu);
        if(a==1) {
            return true;
        }
        else{
            return false;
        }
    }
}
