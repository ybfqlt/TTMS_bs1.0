package com.coco.controller;

import com.coco.entity.Reeorder;
import com.coco.entity.Schedule;
import com.coco.service.OrdersService;
import com.coco.service.ScheduleService;
import com.coco.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.faces.annotation.RequestMap;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname OrdersController
 * @Description TODO
 * @Date 19-6-11 上午11:30
 * @Created by xns
 */

@RestController
@RequestMapping(value="/in/person")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ScheduleService scheduleService;

    //获取自己的订单信息
    @RequestMapping(value="/getperorder",method = RequestMethod.GET)
    public Map<String,Object> Getperorder(HttpServletRequest request){
        String name = (String)request.getSession().getAttribute("user");
       Map<String,Object> ma = new HashMap<>();
        List<Reeorder> a =ordersService.selectper(name);
       ma.put("orderCount",a.size());
       ma.put("order",a);
       return ma;
    }
}
