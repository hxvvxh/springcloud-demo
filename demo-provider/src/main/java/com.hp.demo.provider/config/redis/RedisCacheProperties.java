package com.hp.demo.provider.config.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hp
 * @version 1.0
 * @date 2021/10/31 20:36
 */
@Data
@ConfigurationProperties(prefix = "cache")
public class RedisCacheProperties {
    private Integer defaultExpireTime;

    private Integer userCacheExpireTime;

    private String userCacheName;
}
