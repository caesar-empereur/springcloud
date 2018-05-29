#springcloud 工程说明文档

####consul 分支现在是稳定版本的分支

##eureka-register-center 部分说明
```
使用不同配置文件来启动springboot
执行顺序 
```
* java -jar eureka-register-center-1.0.war --spring.profiles.active=one
* java -jar eureka-register-center-1.0.war --spring.profiles.active=two
* 然后访问管理页面http://localhost:8081  http://localhost:8082


##eureka-service 服务提供者 部分说明
```
执行顺序
```
* java -jar eureka-service-1.0.war --spring.profiles.active=one
* java -jar eureka-service-1.0.war --spring.profiles.active=two


##eureka-service-consumer 也就是 ribbon 部分说明
```
http://localhost:9000/consumer
```

##consul 说明
```
页面管理 http://localhost:8500
consul 模块里面的依赖管理 spring-cloud-consul-dependencies 与其他模块的 spring-cloud-dependencies 不一样
猜测是 consul 与 spring-cloud 不是同一套环境，只是 spring-cloud 集成了 consul 
dependencyManagement {
    imports {
        mavenBom 'org.springframework.cloud:spring-cloud-consul-dependencies:2.0.0.RC1'
    }
}
```

##fiegn部分说明

* 访问地址 http://localhost:9005/feign
* 调试时，启动5个spring 应用, 2 个注册中心(高可用，互相注册), 2个服务提供者(互相注册到注册中心)
* 1个 ribbon 负载均衡客户端, 注入 RestTemplate ,获取该对象所维护的服务提供者列表
* ribbon 客户端其实就是一个对象，维护一个可用服务的列表，这次请求用 A 服务，下次请求用 B 服务
    ，因此就是客户端负载均衡，与 服务端负载均衡(nginx)有区别
* 在 EurekaConsumerController 这个类里面的 
   restTemplate.getForEntity("http://EUREKA-SERVICE/service", String.class)实现客户端负载均衡



##整个工程的模块依赖管理
```
关键的有2部分: jar 的版本 和 dependencyManagement 的版本
dependencyManagement 参考 官网的例子，一般都会有 current 的版本
jar 的版本 参考 springcloud 官网的例子和 maven 库里面的对应的版本
```





