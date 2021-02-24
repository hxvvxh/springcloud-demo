package com.hp.demo.provider.service;

import com.hp.demo.provider.api.HpProviderService;
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
public class HpProviderServiceImpl implements HpProviderService {
    @Override
    public Integer getCount(@RequestParam(name = "count") Integer count) {
        System.out.println("HpProviderService[] getCount[] count:"+ count);
        return count+1;
    }
}
