package com.hp.demo.provider.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hp
 * @version 1.0
 * @date 2021/1/13 21:49
 */
@FeignClient(name = "demo-provider",path = "/api/hp/provider")
public interface HpProviderService {


    @GetMapping(value = "/getCount")
    Integer getCount(@RequestParam(name = "count") Integer count);
}
