package com.coco.controller;


import com.coco.entity.Result;
import com.coco.entity.user;
import com.coco.service.LoginService;
import com.coco.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname loginController
 * @Description TODO
 * @Date 19-5-24 下午4:53
 * @Created by ltt
 */

@RestController
@RequestMapping("/up")
public class LoginController {
    /*private Logger logger = Logger.getLogger(LoginController.class);*/

    @Autowired
    private LoginService loginService;

    @Autowired
    private MailService mailservice;

    public static class temp{
        private String name;
        private String password;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
    * @Description login in
    * @return org.springframework.web.servlet.ModelAndView
    *
    **/
    @RequestMapping(value="/login",method= RequestMethod.POST)
    public ModelAndView getlogin(@RequestBody temp log){
        Result res = loginService.judgelogin(log.name,log.password);
        ModelAndView mv = new ModelAndView();
        mv.addObject("loginInfo",res.getJudge());
        mv.addObject("type",res.getMessage());
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    /**
    * @Description 注册firststep
    * @return org.springframework.web.servlet.ModelAndView
    *
    **/
    @RequestMapping(value="/Securityqq",method=RequestMethod.POST)
    public ModelAndView securityQq(@RequestBody String qq){
        Result res = loginService.securityqq(qq);
        ModelAndView mv=new ModelAndView();
        mv.addObject("qqState",res.getJudge());
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    /**
    * @Description send Securitycode
    * @return java.lang.Object
    *
    **/
    @RequestMapping(value="/Securitycode",method = RequestMethod.POST)
    public Object sendSecuritycode(HttpSession session,@RequestBody Map<String,String> map){
        System.out.println(map.get("userqq"));
        Result result = mailservice.SendSecuritycode(map.get("userqq")+"@qq.com");
        System.out.println(result.getMessage());
        session.setAttribute("Securitycode",result.getMessage());
        Map<String,Object> ma = new HashMap<>();
        ma.put("securityCodeState",result.getJudge());
        return ma;
    }

    /**
    * @Description Verify that the verification code is entered correctly and the name is available
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/Senameandcode",method = RequestMethod.POST)
    public Map<String,Object> SecurityName(HttpSession session, @RequestBody Map<String,String> map){
        System.out.println(map.get("username")+" "+session.getAttribute("Securitycode"));
        Boolean result = mailservice.Securitycodeandname(map.get("username"));
        Map<String,Object> ma = new HashMap<>();
        ma.put("nameState",result);
        if(map.get("securityCode").equals(session.getAttribute("Securitycode"))){
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