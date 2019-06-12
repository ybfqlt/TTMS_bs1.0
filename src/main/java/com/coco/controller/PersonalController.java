package com.coco.controller;

import com.coco.entity.user;
import com.coco.service.OrdersService;
import com.coco.service.UsermanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname PersonalController
 * @Description TODO
 * @Date 19-6-11 下午4:07
 * @Created by xns
 */
@RestController
@RequestMapping(value="/in/person")
public class PersonalController {
    @Autowired
    private UsermanageService usermanageService;

    @Autowired
    private OrdersService ordersService;

    /**
    * @Description 个人资料页面
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/perinformation",method = RequestMethod.GET)
    public Map<String,Object> getInformation(HttpServletRequest request){
        Map<String,Object> ma = new HashMap<>();
        String name = (String)request.getSession().getAttribute("user");
        user uu = usermanageService.getUser(name);
        ma.put("userState",request.getSession().getAttribute("login"));
        ma.put("userName",request.getSession().getAttribute("user"));
        ma.put("userQq",uu.getUserQq());
        ma.put("userType",uu.getUserType());
        ma.put("userDate",uu.getUserRegistertime());
        ma.put("day",usermanageService.getDay(name));
        Map<String,Integer> maa= ordersService.getper(uu.getUserId());
        ma.put("ticketAll",maa.get("ticketAll"));
        ma.put("ticketNo",maa.get("ticketNo"));
        ma.put("movieCount",maa.get("movieCount"));
        return ma;
    }
}
