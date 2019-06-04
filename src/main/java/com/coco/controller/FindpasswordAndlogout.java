package com.coco.controller;

import com.coco.entity.Result;
import com.coco.service.FindpasswordAndlogoutService;
import com.coco.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname FindpasswordAndlogout
 * @Description
 * @Date 19-6-1 下午9:15
 * @Created by ltt
 */

@RestController
@RequestMapping("/up")
public class FindpasswordAndlogout {
    @Autowired
    private FindpasswordAndlogoutService findpasswordAndlogoutService;

    @Autowired
    private MailService mailservice;

    /**
    * @Description Check whether the qq or user name entered by the user exists.
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/forgotpassword",method= RequestMethod.POST)
    public Map<String,Object> sendSercuritycode(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        boolean judge;
        Map<String, Object> ma = new HashMap<>();
        /*System.out.println((Boolean)map.get("dataType"))*/;
        //输入的是用户名
        if ((Boolean) map.get("dataType") == true) {
            judge = findpasswordAndlogoutService.findname((String) map.get("data"));
            if (judge == true) {
                String qq = findpasswordAndlogoutService.returnqq((String) map.get("data"));
                Result result = mailservice.SendSecuritycode(qq+"@qq.com");
                System.out.println(result.getMessage());//测试
                request.getSession().setAttribute("Securitycode", result.getMessage());
                ma.put("sendSecurityCodeState", result.getJudge());
            } else {
                ma.put("sendSecurityCodeState",false);
            }
        }
        //qq
        else {
            judge = findpasswordAndlogoutService.findqq((String) map.get("data"));
            if (judge == true) {
                Result result = mailservice.SendSecuritycode(map.get("data") + "@qq.com");
                System.out.println(result.getMessage());//测试
                request.getSession().setAttribute("Securitycode", result.getMessage());
                ma.put("sendSecurityCodeState", result.getJudge());
            } else {
                ma.put("sendSecurityCodeState",false);
            }
        }
        return ma;
    }

    /**
    * @Description Verify that the verification code entered by the user is correct
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/seecoderight",method=RequestMethod.POST)
    public Map<String,Object> SecurityCode(HttpServletRequest request, @RequestBody Map<String,String> map){
        //测试
        /*System.out.println(request.getSession().getAttribute("Securitycode"));*/
        Map<String,Object> ma = new HashMap<>();
        if(map.get("securityCode").equals(request.getSession().getAttribute("Securitycode"))){
            ma.put("securityCodeState", true);
        }
        else {
            ma.put("securityCodeState", false);
        }
        return ma;
    }

    /**
    * @Description Update the password in the database
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * 
    **/
    @RequestMapping(value="/changepassword",method=RequestMethod.POST)
    public Map<String,Object> Changepassword(@RequestBody Map<String,Object> map){
        boolean judge;
        Map<String, Object> ma = new HashMap<>();
        //输入的是用户名
        if ((Boolean) map.get("dataType") == true) {
            judge=findpasswordAndlogoutService.changePasswordByname((String)map.get("data"),(String)map.get("newPassword"));
        }
        else{
            judge=findpasswordAndlogoutService.changePasswordByqq((String)map.get("data"),(String)map.get("newPassword"));
        }
        ma.put("state",judge);
        return ma;
    }
}
