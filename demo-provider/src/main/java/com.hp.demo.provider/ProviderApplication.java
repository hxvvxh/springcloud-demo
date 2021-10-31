package com.hp.demo.provider;

import com.google.common.base.Stopwatch;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author hp
 * @version 1.0
 * @date 2021/1/13 21:45
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = {
        "com.hp.demo.provider.dao.mapper"
})
public class ProviderApplication {
    public static void main(String[] args) {
        Stopwatch stopwatch=Stopwatch.createStarted();
        SpringApplication.run(ProviderApplication.class,args);
        System.out.println("Provider Starter Up : " + stopwatch);
    }
}
