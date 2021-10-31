package com.hp.demo.provider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hp
 * @version 1.0
 * @date 2021/10/31 20:54
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedisDo<T> implements Serializable {

    private T entity;
}
