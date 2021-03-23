package com.hp.demo.provider.contorller;

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
@RequestMapping("/api/hp/hpProvider")
public class HpProviderController {
    @Autowired
    private HpProviderService hpProviderService;

    @GetMapping("/getCount")
    public String getCount(@RequestParam(value = "count") Integer count){
        return hpProviderService.getCount(count);
    }
}
