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
 * @Created by xns
 */

@RestController
@RequestMapping("/up")
public class FindpasswordAndlogout {
    @Autowired
    private FindpasswordAndlogoutService findpasswordAndlogoutService;

    @Autowired
    private MailService mailservice;

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Description Check whether the qq or user name entered by the user exists.
     **/
    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public Map<String, Object> sendSercuritycode(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        boolean judge;
        Map<String, Object> ma = new HashMap<>();
        /*System.out.println((Boolean)map.get("dataType"))*/
        ;
        //输入的是用户名
        if ((Boolean) map.get("dataType") == true) {
            judge = findpasswordAndlogoutService.findname((String) map.get("data"));
            if (judge == true) {
                String qq = findpasswordAndlogoutService.returnqq((String) map.get("data"));
                Result result = mailservice.SendSecuritycode(qq + "@qq.com");
                System.out.println(result.getMes());//测试
                request.getSession().setAttribute("Securitycode", result.getMes());
                ma.put("sendSecurityCodeState", result.getJudge());
            } else {
                ma.put("sendSecurityCodeState", false);
            }
        }
        //qq
        else {
            judge = findpasswordAndlogoutService.findqq((String) map.get("data"));
            if (judge == true) {
                Result result = mailservice.SendSecuritycode(map.get("data") + "@qq.com");
                System.out.println(result.getMes());//测试
                request.getSession().setAttribute("Securitycode", result.getMes());
                ma.put("sendSecurityCodeState", result.getJudge());
            } else {
                ma.put("sendSecurityCodeState", false);
            }
        }
        return ma;
    }

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Description Verify that the verification code entered by the user is correct
     **/
    @RequestMapping(value = "/seecoderight", method = RequestMethod.POST)
    public Map<String, Object> SecurityCode(HttpServletRequest request, @RequestBody Map<String, String> map) {
        //测试
        /*System.out.println(request.getSession().getAttribute("Securitycode"));*/
        Map<String, Object> ma = new HashMap<>();
        if (map.get("securityCode").equals(request.getSession().getAttribute("Securitycode"))) {
            ma.put("securityCodeState", true);
        } else {
            ma.put("securityCodeState", false);
        }
        return ma;
    }

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Description Update the password in the database
     **/
    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public Map<String, Object> Changepassword(@RequestBody Map<String, Object> map) {
        boolean judge;
        Map<String, Object> ma = new HashMap<>();
        //输入的是用户名
        if ((Boolean) map.get("dataType") == true) {
            judge = findpasswordAndlogoutService.changePasswordByname((String) map.get("data"), (String) map.get("newPassword"));
        } else {
            judge = findpasswordAndlogoutService.changePasswordByqq((String) map.get("data"), (String) map.get("newPassword"));
        }
        ma.put("state", judge);
        return ma;
    }

    /**
     * @return void
     * @Description logout
     **/
    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public Map<String, Object> Signout(HttpServletRequest request) {
        Map<String, Object> ma = new HashMap<>();
        if (request.getSession(false) != null) {
            request.getSession(false).invalidate();
            ma.put("signoutState", true);
            ma.put("msg", "登出成功!!!");
        } else {
            ma.put("signoutState", false);
            ma.put("msg", "未登录，哪来登出!!!");
        }
        return ma;
    }

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Description logout
     **/
    @RequestMapping(value = "/outbefore", method = RequestMethod.GET)
    public Map<String, Object> Logoutbefore(HttpServletRequest request) {
        Map<String, Object> ma = new HashMap<>();
        Result res = findpasswordAndlogoutService.logout((String) request.getSession().getAttribute("user"));
        if (res.getJudge() == false) {
            ma.put("sendSecurityCodeState", false);
            ma.put("msg", "session error and no user");
        } else {
            Result result = mailservice.SendSecuritycode(res.getMes() + "@qq.com");
            System.out.println(result.getMes());//测试
            request.getSession().setAttribute("Securitycode", result.getMes());
            ma.put("sendSecurityCodeState", result.getJudge());
            ma.put("msg", "验证码发送成功，请查收，请注意垃圾邮箱!!!");
        }
        return ma;
    }

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Description logout返回验证码的状态，注销是否成功and msg
     **/
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Map<String, Object> Logout(HttpServletRequest request, @RequestBody Map<String, String> map) {
        Map<String, Object> ma = new HashMap<>();
        if (map.get("securityCode").equals(request.getSession().getAttribute("Securitycode"))) {
            ma.put("securityCodeState", true);
            if (request.getSession(false) != null) {
                request.getSession(false).invalidate();
                ma.put("signoutState", true);
                ma.put("msg", "注销成功");
            } else {
                ma.put("signoutState", false);
                ma.put("msg", "get session null");
            }
        } else {
            ma.put("securityCodeState", false);
            ma.put("msg", "注销失败,验证码不匹配!!!");
        }
        return ma;
    }


    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Description 已经登陆的用户修改密码
     **/
    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
    public Map<String, Object> UpdatePassword(HttpServletRequest request, @RequestBody Map<String, String> map) {
        Map<String, Object> ma = new HashMap<>();
        String name = (String) request.getSession().getAttribute("user");
        if (name == null) {
            ma.put("updateState", false);
            ma.put("msg", "session occur problem");
        } else {
            Boolean judge = findpasswordAndlogoutService.changePasswordByname(name, map.get("newPassword"));
            if (judge == true) {
                ma.put("updateState", true);
                ma.put("msg", "密码修改成功!!!");
            } else {
                ma.put("updateState", false);
                ma.put("msg", "密码修改失败!!!");
            }
        }
        return ma;
    }
}
