package com.hp.demo.provider.contorller;

import com.hp.demo.provider.config.ProviderProperties;
import com.hp.demo.provider.config.ProviderSwitch;
import com.hp.demo.provider.dao.entity.HpProviderEntity;
import com.hp.demo.provider.dao.mapper.HpProvideDo;
import com.hp.demo.provider.entity.RedisDo;
import com.hp.demo.provider.entity.redis.HpRedisStr;
import com.hp.demo.provider.redis.RedisUtils;
import com.hp.demo.provider.service.HpProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @author hp
 * @version 1.0
 * @date 2021/1/13 22:07
 */
@RestController
@RequestMapping
@Slf4j
public class HpProviderController {

    @Autowired
    private HpProviderService providerService;
    @GetMapping("/version")
    public String version(){
        return providerService.version();
    }

    @Autowired
    private ProviderProperties properties;
    @GetMapping("/properties")
    public ProviderProperties properties(){
        return properties;
    }

    @Autowired
    private ProviderSwitch providerSwitch;

    @GetMapping("/switchs")
    public ProviderSwitch switchs(){
        return providerSwitch;
    }

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/addRedis/{key}")
    public Boolean addRedis(@PathVariable("key") String key,
                            @RequestBody(required = true) HpRedisStr redisStr){
        return redisUtils.addStr(key, RedisDo.builder().entity(redisStr).build());
    }

    @PostMapping("/getRedis/{key}")
    public HpRedisStr getRedis(@PathVariable("key") String key){
        return redisUtils.getValue(key,HpRedisStr.class);
    }

    @Cacheable(key = "#name",value = "cache.dir")
    @GetMapping("/cacheStr")
    public HpRedisStr cacheStr(@RequestParam("name") String name){
        log.info("HpProviderController cacheStr start, name:{}",name);
        return HpRedisStr.builder().name(name).age(100).build();
    }


    @Autowired
    private HpProvideDo hpProvideDo;

    @PostMapping("/insert")
    public Boolean insert(@RequestBody HpProviderEntity entity){
        return hpProvideDo.insert(entity);
    }

    @PostMapping("/getById")
    public HpProviderEntity getById(@RequestParam("id") Long id){
        return hpProvideDo.getById(id);
    }
}
