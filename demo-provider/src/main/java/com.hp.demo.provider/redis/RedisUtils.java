package com.hp.demo.provider.redis;

import com.hp.demo.provider.entity.RedisDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author hp
 * @version 1.0
 * @date 2021/10/31 20:11
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    public Boolean addStr(String key,
                          Object value){
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.set(key(key),redisTemplate.getValueSerializer().serialize(value));
            }
        });
        return Boolean.TRUE;
    }

    public <T> T getValue(String key,
                         Class<T> t) {
        RedisDo<T> result = (RedisDo<T>) redisTemplate.execute(new RedisCallback<RedisDo>() {
            @Override
            public RedisDo doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] value = redisConnection.get(key(key));
                Object object = redisTemplate.getValueSerializer().deserialize(value);
                return (RedisDo) object;
            }
        });
        return result.getEntity();
    }
    public byte[] key(String key){
        return redisTemplate.getKeySerializer().serialize(key);
    }

}
