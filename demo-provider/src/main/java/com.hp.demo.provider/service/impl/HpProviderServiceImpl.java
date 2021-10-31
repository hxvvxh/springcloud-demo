package com.hp.demo.provider.service.impl;

import com.hp.demo.provider.service.HpProviderService;
import org.springframework.stereotype.Component;

/**
 * @author hp
 * @version 1.0
 * @date 2021/10/31 19:44
 */
@Component
public class HpProviderServiceImpl implements HpProviderService {
    @Override
    public String version() {
        return "1.0.1-provider";
    }
}
