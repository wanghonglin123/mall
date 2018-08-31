package mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.jdbc.DataSourceHealthIndicatorAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wang.honglin on 2018/8/30.
 */
// springboot 注解
@SpringBootApplication
// 启用发现客户端，消费端使用，必须开启
@EnableDiscoveryClient

// 开启断路器，防止服务恶性循环，比如A出现故障，B调用A，那么会导致B阻塞，如果并发量高
// 会导致服务不可用。开启断路器用于解决这种问题。在方法加入以下注解即可完成断路器功能：
// @HystrixCommand(fallbackMethod = "error")，编写一个回调方法error() 即可。
@EnableCircuitBreaker

// 可用SpringCloudApplication 代替上面的3个注解
//@SpringCloudApplication
// 排除不需要的spring 提供的自动配置类, 否则启动会报错，springboot-2.0.1版本
@EnableAutoConfiguration(exclude = {DataSourceHealthIndicatorAutoConfiguration.class,
        DataSourceAutoConfiguration.class})
public class ConsumeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumeApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
