package com.coco.controller;

import com.coco.entity.Reorder;
import com.coco.entity.Salestatistics;
import com.coco.service.OrdersService;
import com.coco.service.StatisticsService;
import com.coco.service.TicketService;
import com.coco.service.UsermanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname PayController
 * @Description TODO
 * @Date 19-6-10 下午5:05
 * @Created by xns
 */
@RestController
@RequestMapping(value="/in/pay")
public class PayController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UsermanageService usermanageService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private StatisticsService statisticsService;

    /**
    * @Description 支付
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/paymoney",method= RequestMethod.POST)
    public Map<String,Object> StorageOrder(HttpServletRequest request, @RequestBody List<Reorder> lists){
        String name = (String)request.getSession().getAttribute("user");
        int id = usermanageService.getuserId(name);
        Boolean judge = ordersService.Addorders(id,lists);
        //添加订单后更新票房
        Boolean judge2 = statisticsService.updateStatistics(lists.size(),lists.get(0).getScheduleId());
        Map<String,Object> ma = new HashMap<>();
        ma.put("payState",judge);
        if(judge2==false){
            ma.put("msg","支付成功,但是票房添加失败!!!!");
        }else{
            ma.put("msg","支付成功，希望您观影愉快!!!");
        }
        return ma;
    }


    /**
    * @Description 退款
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/refund",method=RequestMethod.POST)
    public Map<String,Object> Refund(HttpServletRequest request,@RequestBody Map<String,Integer> map){
        Map<String,Object> ma = new HashMap<>();
        String name = (String)request.getSession().getAttribute("user");
        Boolean judge1=ordersService.updateOrder(map.get("orderId"));
        Boolean judge2=ticketService.UpdateTicketByorderid(map.get("orderId"));
        Boolean judge3=statisticsService.updaterefundStatistics(map.get("orderId"));
        if(judge1==true&&judge2==true&&judge3==true){
            ma.put("refund",true);
            ma.put("msg","退款成功!!!");
        }
        else{
            ma.put("refund",false);
            ma.put("msg","退款失败!!!");
        }
        return ma;
    }
}
