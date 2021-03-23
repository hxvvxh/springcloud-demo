# hp-springcloud-demo

## 使用方式：

#### 一：启动nacos服务

nacos控制台地址 http://127.0.0.1:8848/nacos/#/login

#### 二：nacos添加配置文件

hp-provoder.yaml

```yaml
server:
  port: 8181
management:
  endpoints:
    web:
      exposure:
        include: '*'
```

hp-consumer.yaml

```yaml
server:
  port: 8282
#添加sentinel依赖后 暴露http://127.0.0.1:8282/actuator/sentinel端点
management:
  endpoints:
    web:
      exposure:
        include: '*'
ribbon:
  eager-load:
    enabled: true
    clients:
      - demo-provider      
```

hp-flow-rules.json

```json
[
    {
    "app":"demo-consumer",    
    "resource": "getTest",
    "limitApp":"default",
    "grade":1,
    "count":2,
    "strategy":0,
    "controlBehavior":0,
    "clusterMode":false
    }
]
```

#### 三：修改bootstrap.yml

​		demo-provider/demo-consumer 模块下 resources下bootstrap.yml配置文件

​		nacos相关配置替换成自己nacos的配置

#### 四：下载Sentinel

​		https://github.com/alibaba/Sentinel

​		进入：[sentinel-dashboard](https://github.com/alibaba/Sentinel/tree/master/sentinel-dashboard)  

​		mvn package 得到jar包后。java -jar sentinel-dashboard.jar 启动控制台

​		sentinel控制台址 http://127.0.0.1:8080/#/dashboard/home

​		控制台配置限流降级策略

#### 五：测试

​		com.hp.demo.provider.contorller.HpConsumerController

​		限流测试 http://127.0.0.1:8282/api/hp/hpConsumer/getTestHp?count=1

 	    降级测试 http://127.0.0.1:8282/api/hp/hpConsumer/getTestFallback?count=1

#### 六：源码分析
     https://github.com/alibaba/Sentinel/blob/master/sentinel-core/src/main/java/com/alibaba/csp/sentinel/SphU.java
     
     com.alibaba.csp.sentinel.SphU.entry(java.lang.String)    	    
