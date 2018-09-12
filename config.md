eureka 作为注册中心的配置
compile "org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:$version_spring_cloud"
compile "org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:$version_spring_cloud"


dependencyManagement {
    imports {
//        mavenBom 'org.springframework.cloud:spring-cloud-consul-dependencies:2.0.0.RC1'
    }
}
