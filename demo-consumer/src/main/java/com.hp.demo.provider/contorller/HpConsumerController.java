package com.hp.demo.provider.contorller;

import com.alibaba.csp.sentinel.EntryType;
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
    public String getCount(@RequestParam(value = "count") Integer count){
        return hpProviderService.getCount(count);
    }

    /**
     * 限流
     * @param count
     * @return
     */
    @GetMapping("/getTestHp")
    @SentinelResource(
            value = "getTest",
            blockHandler = "blockHandler1",
            entryType = EntryType.OUT
    )
    public String getTestBlockHandler(@RequestParam(value = "count") Integer count){
        return hpProviderService.getCount(count);
    }

    /**
     * 降级
     * @param count
     * @return
     */
    @GetMapping("/getTestFallback")
    @SentinelResource(
            value = "getTestFallback",
            fallback = "fallback1"
    )
    public String getTestFallback(@RequestParam(value = "count") Integer count){
        throw new RuntimeException("hahaha");
    }

    public String blockHandler1(Integer count,BlockException ex){
        System.out.println("getTest1[] blockHandler1[] count"+ count);
        return "blockHandler";
    }

    public String fallback1(Integer count){
        System.out.println("getTest1[] fallback1[] count"+ count);
        return "fallback";
    }




}
