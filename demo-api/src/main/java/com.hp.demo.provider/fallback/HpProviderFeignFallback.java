package com.hp.demo.provider.fallback;

import com.hp.demo.provider.api.HpProviderFeignService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hp
 * @version 1.0
 * @date 2021/2/25 23:08
 */
public class HpProviderFeignFallback implements HpProviderFeignService {
    @Override
    public String getCount(@RequestParam(name = "count") Integer count) {
        System.out.println("HpProviderFallback[]" +count);
        return "102";
    }
}
