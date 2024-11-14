package com.ozz.sharding.web;

import com.ozz.sharding.service.MyService;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/my")
public class MyWebController {
    @Resource
    private MyService myService;

    @GetMapping(value = "/test", produces = "application/json")
    public List<String> test() {
//        return myService.selectById(1L);
//        return myService.selectByIdSharding(1L);
//        return myService.selectByIdSharding(0L);
//        return myService.selectByIdSharding(null);
        try {
            HintManager.getInstance().setWriteRouteOnly();
            return myService.selectByIdSharding(null);
        } finally {
            HintManager.clear();
        }
    }
}
