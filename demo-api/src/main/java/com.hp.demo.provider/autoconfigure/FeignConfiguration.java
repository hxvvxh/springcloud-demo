package com.hp.demo.provider.autoconfigure;

import com.hp.demo.provider.fallback.HpProviderFeignFallback;
import org.springframework.context.annotation.Bean;

/**
 * @author hp
 * @version 1.0
 * @date 2021/2/25 23:07
 */
public class FeignConfiguration {

    @Bean
    public HpProviderFeignFallback hpProviderFallback(){
        return new HpProviderFeignFallback();
    }
}
