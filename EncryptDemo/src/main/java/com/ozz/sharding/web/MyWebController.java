package com.ozz.sharding.web;

import com.ozz.sharding.service.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/my")
public class MyWebController {
    @Resource
    private MyService myService;

    @GetMapping(value = "/test", produces = "application/json")
    public List<Map<String, Object>> test() {
        return myService.select();
    }
}
