package com.coco.controller;

import com.coco.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
