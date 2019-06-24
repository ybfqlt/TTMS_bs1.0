package com.coco.controller;

import com.coco.entity.Orders;
import com.coco.entity.Reorder;
import com.coco.entity.Result;
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
import java.util.LinkedList;
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
    private TicketService ticketService;

    @Autowired
    private UsermanageService usermanageService;


    @Autowired
    private StatisticsService statisticsService;

    /**
     * @Description 下单
     * @return java.util.Map<java.lang.String,java.lang.Object>
     *
     **/
    @RequestMapping(value="/saveorder",method= RequestMethod.POST)
    public Map<String,Object> StorageOrder(HttpServletRequest request, @RequestBody Map<String,Object> map){
        String name = (String)request.getSession().getAttribute("user");
        int id = usermanageService.getuserId(name);
        String la = (String)map.get("data");
        List<Reorder> lists = new LinkedList<>();
        int row=0,col = 0,j=0;
        String[] aa = la.split("排|列");
        for(int i=0;i<aa.length;i+=2){
            row=Integer.valueOf(aa[i]);
            col=Integer.valueOf(aa[i+1]);
            Reorder aaa = new Reorder(row,col);
            lists.add(aaa);
        }
        Result res = ordersService.Addorders(id,(Integer)map.get("scheduleId"),lists);
        Map<String,Object> ma = new HashMap<>();
        StringBuilder s = new StringBuilder();
        List<Integer> list = (List<Integer>)res.getMes();
        for(int t=0;t<list.size();t++){
            s.append(list.get(t));
            s.append("t");
        }

        ma.put("payState",res.getJudge());
        ma.put("id",s.toString());
        ma.put("count",list.size());
        ma.put("scheduleId",map.get("scheduleId"));
        return ma;
    }

    /**
     * @Description 支付
     * @return java.util.Map<java.lang.String,java.lang.Object>
     *
     **/
    @RequestMapping(value="/paymoney",method= RequestMethod.POST)
    public Map<String,Object> Paymoney(HttpServletRequest request, @RequestBody Map<String,Object> map){
        Map<String,Object> ma = new HashMap<>();
        if((Boolean)map.get("state") == true ) {
            ordersService.updateOrderf((String) map.get("id"));
            //添加订单后更新票房
            Boolean judge2 = statisticsService.updateStatistics((Integer)map.get("count"),(Integer)map.get("scheduleId"));
            ma.put("state",judge2);
            ma.put("msg","支付成功!!!");
        }
        else{
            ordersService.deleteByid((String)map.get("data"));
            ma.put("state",false);
            ma.put("msg","支付失败,请重新下单!!!");
        }
        return ma;
    }


    /**
    * @Description 前端请求关闭座位页面
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/free",method=RequestMethod.POST)
    public Map<String,Object> reState(@RequestBody Map<String,Integer> map){
        Map<String,Object> ma = new HashMap<>();
        Orders order= ordersService.getorderById(map.get("orderId"));
        if(order.getOrdersType()==1){
            ma.put("State",true);
        }
        else{
            ma.put("State",false);
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
