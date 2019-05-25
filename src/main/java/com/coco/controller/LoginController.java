package com.coco.controller;

import com.coco.entity.user;
import com.coco.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @Classname loginController
 * @Description TODO
 * @Date 19-5-24 下午4:53
 * @Created by ltt
 */

@RestController
@RequestMapping("/up")
public class LoginController {
    private Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    public static class log{
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
    @RequestMapping(value="/login",method= RequestMethod.POST)
    public ModelAndView getlogin(@RequestBody log logl){
        System.out.println(logl.name+"   "+logl.password);
        Boolean judge = loginService.judgelogin(logl.name,logl.password);
        ModelAndView mv = new ModelAndView();
        mv.addObject(judge);
        mv.setViewName("/views/success");
        return mv;
    }

    /**
    * @Description 注册
    * @return org.springframework.web.servlet.ModelAndView
    *
    **/
    @RequestMapping(value="/register",method=RequestMethod.POST)
    public ModelAndView getRegister(@RequestBody user users){
        System.out.println(users);
        Boolean succes = loginService.judgeregister(users);
        ModelAndView mv=new ModelAndView();
        mv.addObject(succes);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }
}