package com.coco.controller;

import com.coco.entity.Reorder;
import com.coco.service.OrdersService;
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
@RequestMapping(value="/in")
public class PayController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UsermanageService usermanageService;

    /**
    * @Description 支付
    * @return java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    @RequestMapping(value="/pay",method= RequestMethod.POST)
    public Map<String,Object> StorageOrder(HttpServletRequest request, @RequestBody List<Reorder> lists){
        String name = (String)request.getSession().getAttribute("user");
        int id = usermanageService.getuserId(name);
        Boolean judge = ordersService.Addorders(id,lists);
        Map<String,Object> ma = new HashMap<>();
        ma.put("payState",true);
        ma.put("msg","支付成功，希望您观影愉快!!!");
        return ma;
    }


}
