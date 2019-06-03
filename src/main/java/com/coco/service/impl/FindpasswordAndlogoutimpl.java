package com.coco.service.impl;

import com.coco.dao.userMapper;
import com.coco.entity.user;
import com.coco.service.FindpasswordAndlogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname FindpasswordAndlogoutimpl
 * @Description TODO
 * @Date 19-6-2 上午10:54
 * @Created by ltt
 */
@Service("FindpasswordAndlogoutService")
public class FindpasswordAndlogoutimpl implements FindpasswordAndlogoutService {

    @Autowired
    private userMapper usermapper;

    /**
    * @Description Check if qq exists
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean findqq(String userQq){
        user uu = usermapper.selectByuserQq(userQq);
        if(uu == null){
            return false;
        }
        else
            return true;
    }
    
    /**
    * @Description Check if name exists
    * @return java.lang.Boolean
    * 
    **/
    @Override
    public Boolean findname(String userName){
        user uu = usermapper.selectByuserName(userName);
        if(uu == null){
            return false;
        }
        else
            return true;
    }
    
    /**
    * @Description Return qq according to name
    * @return java.lang.String
    * 
    **/
    @Override
    public String returnqq(String userName){
        user uu = usermapper.selectByuserName(userName);
        String qq = uu.getUserQq();
        return qq;
    }

    /**
    * @Description According to the qq number and the new password
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean changePasswordByqq(String userQq,String password){
        user uu = new user();
        uu.setUserQq(userQq);
        uu.setUserPassword(password);
        if(usermapper.updatepasswordByQq(uu)!=0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
    * @Description According to the name and the new password
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean changePasswordByname(String userName,String password){
        user uu = new user();
        uu.setUserName(userName);
        uu.setUserPassword(password);
        if(usermapper.updatepassworduserName(uu)!=0){
            return true;
        }
        else {
            return false;
        }
    }
}
