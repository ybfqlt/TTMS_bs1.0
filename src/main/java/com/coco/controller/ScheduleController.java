package com.coco.controller;

import com.coco.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname ScheduleController
 * @Description TODO
 * @Date 19-6-8 下午4:09
 * @Created by xns
 */
@RestController
@RequestMapping(value="/in/jingli")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

}
