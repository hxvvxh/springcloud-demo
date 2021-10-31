package com.hp.demo.provider.api;

import com.hp.demo.provider.autoconfigure.FeignConfiguration;
import com.hp.demo.provider.fallback.HpProviderFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hp
 * @version 1.0
 * @date 2021/1/13 21:49
 */
@FeignClient(name = "demo-provider",
        path = "/api/hp/provider",
        fallback = HpProviderFeignFallback.class,
        configuration = FeignConfiguration.class)
public interface HpProviderFeignService {


    @GetMapping(value = "/getCount",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String getCount(@RequestParam(name = "count") Integer count);
}
