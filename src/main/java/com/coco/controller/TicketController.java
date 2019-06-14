package com.coco.controller;

import com.coco.service.TicketService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname TicketController
 * @Description TODO
 * @Date 19-6-9 下午2:18
 * @Created by xns
 */

@RestController
@RequestMapping(value="/in/jingli")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @RequestMapping(value="/getticket",method= RequestMethod.POST)
    public Map<String,Object> Getticket(@RequestBody Map<String,Long> map){
        Map<String,Object> ms = ticketService.selectById(map.get("ticketId"));
        return ms;
    }
}
