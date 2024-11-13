package com.ozz.sharding.web;

import com.ozz.sharding.service.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/my")
public class MyWebController {
    @Resource
    private MyService myService;

    @GetMapping(value = "/test", produces = "application/json")
    public String test() {
//        return myService.selectById(1L);
        return myService.selectByIdSharding(1L);
    }
}
