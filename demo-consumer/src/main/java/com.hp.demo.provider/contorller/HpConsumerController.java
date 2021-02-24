package com.hp.demo.provider.contorller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hp.demo.provider.api.HpProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hp
 * @version 1.0
 * @date 2021/1/13 22:07
 */
@RestController
@RequestMapping("/api/hp/hpConsumer")
public class HpConsumerController {
    @Autowired
    private HpProviderService hpProviderService;

    @GetMapping("/getProviderCount")
    public Integer getCount(@RequestParam(value = "count") Integer count){
        return hpProviderService.getCount(count);
    }

    @GetMapping("/getTest1")
    @SentinelResource(
            value = "getTest1",
            blockHandler = "blockHandler1",
            fallback = "fallback1"
    )
    public Integer getTest1(@RequestParam(value = "count") Integer count){
        return hpProviderService.getCount(count);
    }

    public Integer blockHandler1(Integer count){
        System.out.println("getTest1[] blockHandler1[] count"+ count);
        return 100000;
    }

    public Integer fallback1(Integer count, BlockException e){
        System.out.println("getTest1[] fallback1[] count"+ count);
        return -1;
    }

}
