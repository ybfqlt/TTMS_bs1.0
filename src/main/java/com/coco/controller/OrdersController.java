package com.coco.controller;

import com.coco.service.OrdersService;
import com.coco.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname OrdersController
 * @Description TODO
 * @Date 19-6-11 上午11:30
 * @Created by xns
 */

@RestController
@RequestMapping(value="/in")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private TicketService ticketService;
}
