#springcloud

****eureka-register-center 部分说明****
使用不同配置文件来启动springboot
执行顺序 
java -jar eureka-register-center-1.0.war --spring.profiles.active=one
java -jar eureka-register-center-1.0.war --spring.profiles.active=two
启动一个时会出现服务找不到的情况, 2个服务都启动就不会了
然后访问管理页面
http://localhost:8081  http://localhost:8082

****eureka-service 服务提供者 部分说明****
执行顺序
java -jar eureka-service-1.0.war --spring.profiles.active=one
java -jar eureka-service-1.0.war --spring.profiles.active=two

****eureka-service-consumer 也就是 ribbon 部分说明****

调试时，启动5个spring 应用, 2 个注册中心(高可用，互相注册), 2个服务提供者(互相注册到注册中心)
1个 ribbon 负载均衡客户端, 注入 RestTemplate ,获取该对象所维护的服务提供者列表
ribbon 客户端其实就是一个对象，维护一个可用服务的列表，这次请求用 A 服务，下次请求用 B 服务
，因此就是客户端负载均衡，与 服务端负载均衡(nginx)有区别
在 EurekaConsumerController 这个类里面的 restTemplate.getForEntity("http://EUREKA-SERVICE/service", String.class)
实现客户端负载均衡



**eureka-service-consumer部分说明**
http://localhost:9000/consumer

**fiegn部分说明**
访问地址
http://localhost:9005/feign
