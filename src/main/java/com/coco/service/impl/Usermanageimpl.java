package com.coco.service.impl;

import com.coco.dao.userMapper;
import com.coco.entity.Result;
import com.coco.entity.user;
import com.coco.service.UsermanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Classname Usermanageimpl
 * @Description manageUser
 * @Date 19-6-7 上午10:32
 * @Created by xns
 */
@Service("UsermanageService")
public class Usermanageimpl implements UsermanageService {

    @Autowired
    private userMapper usermapper;

    /**
    * @Description 添加特殊用户
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean addjUser(user uu){
        user userr = usermapper.selectByuserName((uu.getUserName()));
        if(userr != null){
            return false;
        }
        else{
            uu.setUserQq("00000000");
            uu.setUserType("j");
            int a= usermapper.insert(uu);
            if(a == 1){
                return true;
            }
            else{
                return false;
            }
        }
     }

     /**
     * @Description 删除特殊用户
     * @return java.lang.Boolean
     *
     **/
    @Override
    public Boolean deletejUser(String name){
        user userr = usermapper.selectByuserName(name);
        if(userr == null){
            return false;
        }else{
            int a = usermapper.deleteByPrimaryKey(userr.getUserId());
            return true;
        }
    }

    /**
    * @Description 批量删除用户
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Result deletesomejUser(List<String> listname){
        Result res = new Result();
        for(int i=0;i<listname.size();i++){
            String name= listname.get(i);
            user userr = usermapper.selectByuserName(name);
            if(userr == null){
                res.setJudge(false);
                res.setMes("用户"+name+"未找到，导致删除失败且终止!!!");
            }else{
                int a = usermapper.deleteByPrimaryKey(userr.getUserId());
            }
        }
        res.setJudge(true);
        res.setMes("批量删除成功!!!");
        return res;
    }

    /**
    * @Description 修改特殊用户,通过id进行修改
    * @return java.lang.Boolean
    *
    **/
    @Override
    public Boolean modifyjUser(user uu){
        user userr = usermapper.selectByuserName(uu.getUserName());
        if(userr == null){
            return false;
        }else{
            int a = usermapper.updateByuserId(uu);
            return true;
        }
    }

    /**
    * @Description 查询特殊用户
    * @return com.coco.entity.Result
    *
    **/
    @Override
    public Result selectjUser(){
        Result res = new Result();
        List<user> lists =usermapper.selectjAll();
        if(lists == null){
            res.setJudge(false);
            res.setMes("暂时没有数据哦!!!");
        }else{
            res.setJudge(true);
            res.setMes(lists);
        }
        return res;
    }

    /**
    * @Description 查询所有用户
    * @return com.coco.entity.Result
    *
    **/
    @Override
    public Result selectUser(){
        Result res= new Result();
        List<user> lists =usermapper.selectAll();
        if(lists == null){
            res.setJudge(false);
            res.setMes("暂时没有数据哦!!!");
        }else{
            res.setJudge(true);
            res.setMes(lists);
        }
        return res;
    }

    /**
    * @Description 根据用户名称返回用户id
    * @return java.lang.Integer
    *
    **/
    public Integer getuserId(String name){
        user uu = usermapper.selectByuserName(name);
        return uu.getUserId();
    }
}