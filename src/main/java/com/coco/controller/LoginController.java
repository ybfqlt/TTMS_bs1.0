package com.coco.controller;

import com.coco.entity.Result;
import com.coco.entity.user;
import com.coco.service.LoginService;
import com.coco.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname loginController
 * @Description TODO
 * @Date 19-5-24 下午4:53
 * @Created by xns
 */

@RestController
@RequestMapping("/up")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private MailService mailservice;

    /**
    * @Description login in
    * @return org.springframework.web.servlet.ModelAndView
    *
    **/
    @RequestMapping(value="/login",method= RequestMethod.POST)
    public Map<String,Object> getlogin(HttpServletRequest request,@RequestBody Map<String,String> map){
        Result res = loginService.judgelogin(map.get("name"),map.get("password"));
        Map ma = new HashMap();
        if(res.getJudge()==true){
            ma.put("loginInfo",res.getJudge());
            ma.put("type",res.getMes());
            ma.put("msg","登陆成功，欢迎使用!!!");
            request.getSession().setAttribute("user",map.get("name"));
            request.getSession().setAttribute("login",true);
            request.getSession().setAttribute("type",res.getMes());
        }
        else{
            ma.put("loginInfo",res.getJudge());
            ma.put("type",res.getMes());
        }
        return ma;
    }

    /**
    * @Description 每一个页面得到用户信息，还是太年轻，早知道让前端直接从session取
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/loginuserlog",method=RequestMethod.GET)
    public Map<String,Object> getCurrentUser(HttpServletRequest request){
        Map ma = new HashMap();
        ma.put("userName",request.getSession().getAttribute("user"));
        ma.put("userState",request.getSession().getAttribute("login"));
        ma.put("userType",request.getSession().getAttribute("type"));
        return ma;
    }

    /**
    * @Description 验证qq并且send Securitycode
    * @return org.springframework.web.servlet.ModelAndView
    *
    **/
    @RequestMapping(value="/Securityqqtocode",method=RequestMethod.POST)
    public Map<String,Object> securityQq(HttpServletRequest request, @RequestBody Map<String, String> map){
        Result res = loginService.securityqq(map.get("qq"));
        Map<String,Object> ma = new HashMap<>();
        if(res.getJudge()==true){
            ma.put("qqState",res.getJudge());
            Result result = mailservice.SendSecuritycode(map.get("qq")+"@qq.com");
            System.out.println(result.getMes());
            request.getSession().setAttribute("Securitycode",result.getMes());
            /*ma.put("securityCodeState",result.getJudge());*/
            /*ma.put("Securitycode",result.getMes());*/
        }
        else {
            ma.put("qqState",false);
        }
        return ma;
    }

    /**
    * @Description Verify that the verification code is entered correctly and the name is available
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/Senameandcode",method = RequestMethod.POST)
    public Map<String,Object> SecurityName(HttpServletRequest request, @RequestBody Map<String,String> map){
        System.out.println(map.get("username")+" "+request.getSession().getAttribute("Securitycode"));
        Boolean result = mailservice.Securitycodeandname(map.get("username"));
        Map<String,Object> ma = new HashMap<>();
        ma.put("nameState",result);
        //注意，多余的
        /*ma.put("Securitycode",request.getSession().getAttribute("Securitycode"));*/
        if(map.get("securityCode").equals(request.getSession().getAttribute("Securitycode"))){
            ma.put("securityCodeState", true);
        }
        else {
            ma.put("securityCodeState", false);
        }
        return ma;
    }

    /**
    * @Description User final registration
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/register",method=RequestMethod.POST)
    public Map<String,Object> Registeruser(@RequestBody Map<String,String> map){
        user us = new user();
        us.setUserName(map.get("username"));
        us.setUserQq(map.get("userqq"));
        us.setUserPassword(map.get("userpassword"));
        Boolean judge = loginService.registeruser(us);
        Map<String,Object> ma = new HashMap<>();
        ma.put("registerState",judge);
        return ma;
    }
}