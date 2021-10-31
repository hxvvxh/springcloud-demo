package com.hp.demo.provider.feign;

import com.hp.demo.provider.api.HpProviderFeignService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hp
 * @version 1.0
 * @date 2021/1/13 21:50
 */
@RestController
@ResponseBody
@RequestMapping(value = "/api/hp/provider")
public class HpProviderFeignServiceImpl implements HpProviderFeignService {
    @Override
    public String getCount(@RequestParam(name = "count") Integer count) {
        System.out.println("HpProviderService[] getCount[] count:"+ count);
        Integer i=count+1;
        return i.toString();
    }
}
