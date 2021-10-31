package com.hp.demo.provider.entity.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hp
 * @version 1.0
 * @date 2021/10/31 20:55
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HpRedisStr implements Serializable {
    private String name;
    private String pass;
    private Integer age;
}
