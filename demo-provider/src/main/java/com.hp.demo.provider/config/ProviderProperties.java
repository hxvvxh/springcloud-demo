package com.hp.demo.provider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hp
 * @version 1.0
 * @date 2021/10/31 19:49
 */
@Data
@ConfigurationProperties(prefix = "hp.provider",ignoreInvalidFields = true)
public class ProviderProperties {

    private String switchs;

    private String appName;
}
