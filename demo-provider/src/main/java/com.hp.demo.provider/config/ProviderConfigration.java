package com.hp.demo.provider.config;

import com.hp.demo.provider.config.redis.KryoRedisSerializer;
import com.hp.demo.provider.config.redis.RedisCacheProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author hp
 * @version 1.0
 * @date 2021/10/31 19:48
 */
@Slf4j
@Configuration
@EnableCaching
public class ProviderConfigration {

    /**
     * properties
     * @return
     */
    @Bean
    public ProviderProperties providerProperties(){
        return new ProviderProperties();
    }
    @Bean
    @ConditionalOnProperty(prefix = "hp.provider",value = "switchs",havingValue = "SWITCH-1",matchIfMissing = true)
    public ProviderSwitch providerSwitchOneOrDefault(){
        log.info("ProviderConfigration providerSwitchOneOrDefault start, [] ");
        return ProviderSwitch.builder().switchs("SWITCH-1").build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "hp.provider",value = "switchs",havingValue = "SWITCH-2")
    public ProviderSwitch providerSwitchTwo(){
        log.info("ProviderConfigration providerSwitchTwo start, [] ");
        return ProviderSwitch.builder().switchs("SWITCH-2").build();
    }

    /**
     * redis
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new KryoRedisSerializer(Object.class));
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new KryoRedisSerializer(Object.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    /**
     * redis cache
     * @return
     */
    @Bean
    public RedisCacheProperties redisCacheProperties(){
        return new RedisCacheProperties();
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory lettuceConnectionFactory,
                                     RedisCacheProperties redisCacheProperties) {
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        // 设置缓存管理器管理的缓存的默认过期时间
        defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofSeconds(redisCacheProperties.getDefaultExpireTime()))
                // 设置 key为string序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 设置value为json序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                // 不缓存空值
                .disableCachingNullValues();

        Set<String> cacheNames = new HashSet<>();
        cacheNames.add(redisCacheProperties.getUserCacheName());

        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put(redisCacheProperties.getUserCacheName(), defaultCacheConfig.entryTtl(Duration.ofSeconds(redisCacheProperties.getUserCacheExpireTime())));

        RedisCacheManager cacheManager = RedisCacheManager.builder(lettuceConnectionFactory)
                .cacheDefaults(defaultCacheConfig)
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .build();
        return cacheManager;
    }
}
