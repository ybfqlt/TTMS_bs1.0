package com.coco.controller;

import com.coco.entity.Result;
import com.coco.service.LoginService;
import com.coco.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

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
    * @Description 登录
    * @return org.springframework.web.servlet.ModelAndView
    *
    **/
    @RequestMapping(value="/login",method= RequestMethod.POST,headers = "Accept=application/json")
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

    @RequestMapping(value="/Securitycode",method = RequestMethod.POST,headers="application/json;charset=utf-8")
    public ModelAndView sendSecuritycode(@RequestBody Map<String,Object> map){
        System.out.println(map.get("userQq"));
        ModelAndView mv = new ModelAndView();
        Result result = mailservice.SendSecuritycode(map.get("userQq")+"@qq.com");
        mv.addObject("securityCodeState",result.getJudge());
        mv.addObject("securitycode",result.getMessage());
        return mv;
    }
}