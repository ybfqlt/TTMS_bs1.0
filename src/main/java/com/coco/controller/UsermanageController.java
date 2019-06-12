package com.coco.controller;

import com.coco.entity.Result;
import com.coco.entity.user;
import com.coco.service.UsermanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname UsermanageController
 * @Description
 * @Date 19-6-6 下午1:12
 * @Created by xns
 */
@RestController
@RequestMapping(value="/in/manager")
public class UsermanageController {

    @Autowired
    private UsermanageService usermanageservice;

    /**
    * @Description 添加特殊用户
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/adduser",method = RequestMethod.POST)
    public Map<String,Object> Adduser(@RequestBody user uu){
        Map<String,Object> ma = new HashMap<>();
        Boolean judge = usermanageservice.addjUser(uu);
        if(judge == true){
            ma.put("addState",true);
            ma.put("msg","添加成功!");
        }
        else{
            ma.put("addState",false);
            ma.put("msg","添加失败,用户名已存在!");
        }
        return ma;
    }

    /**
    * @Description 删除某一个特殊用户
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="deleteuser",method = RequestMethod.POST)
    public Map<String,Object> Deleteoneuser(@RequestBody Map<String,String> map){
        Map<String,Object> ma = new HashMap<>();
        Boolean judge = usermanageservice.deletejUser(map.get("username"));
        if(judge == true){
            ma.put("deleteState",true);
            ma.put("msg","删除成功!");
        }
        else{
            ma.put("deleteState",false);
            ma.put("msg","删除失败!");
        }
        return ma;
    }

    /**
    * @Description 批量删除特殊用户的
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="deletesomeuser",method = RequestMethod.POST)
    public Map<String,Object> DeleteSomeUser(@RequestBody Map<String,Object> map){
        Map<String,Object> ma = new HashMap<>();
        Result res = usermanageservice.deletesomejUser((List)map.get("data"));
        if(res.getJudge()==true){
            ma.put("deleteState",true);
            ma.put("msg",res.getMes());
        }
        else{
            ma.put("deleteState",false);
            ma.put("msg",res.getMes());
        }
        return ma;
    }

    /**
    * @Description 修改特殊用户名称或者密码
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/modifyuser",method=RequestMethod.POST)
    public Map<String,Object> Modifyuser(@RequestBody user userr){
        Map<String,Object> ma = new HashMap<>();
        Boolean judge = usermanageservice.modifyjUser(userr);
        if(judge == true){
            ma.put("modifyState",true);
            ma.put("msg","修改成功!!!");
        }
        else{
            ma.put("modifyState",false);
            ma.put("msg","修改失败，此用户不存在");
        }
        return ma;
    }

    /**
    * @Description 查询所有特殊用户
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/selectjuser",method = RequestMethod.GET)
    public Map<String,Object> Showmanageuser(){
        Map<String,Object> ma = new HashMap<>();
        Result res = usermanageservice.selectjUser();
        if(res.getJudge()==true){
            ma.put("selectState",true);
            ma.put("count",((List<user>)res.getMes()).size());
            ma.put("data",res.getMes());
            ma.put("msg","查询到了!");
        }
        else{
            ma.put("selectState",false);
            ma.put("msg",res.getMes());
        }
        return ma;
    }

    /**
    * @Description 查询显示所有普通用户
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value = "/selectuser",method = RequestMethod.GET)
    public Map<String,Object> Showuser(){
        Map<String,Object> ma = new HashMap<>();
        Result res = usermanageservice.selectUser();
        if(res.getJudge()==true){
            ma.put("selectState",true);
            ma.put("msg","查询到了!");
            ma.put("data",res.getMes());
        }
        else{
            ma.put("selectState",false);
            ma.put("msg",res.getMes());
        }
        return ma;
    }

}
