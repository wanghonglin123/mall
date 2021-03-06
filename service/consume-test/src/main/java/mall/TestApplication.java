package mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.jdbc.DataSourceHealthIndicatorAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wang.honglin on 2018/8/31.
 */
@SpringBootApplication
// 排除不需要的spring 提供的自动配置类, 否则启动会报错，springboot-2.0.1版本
@EnableAutoConfiguration(exclude = {DataSourceHealthIndicatorAutoConfiguration.class,
        DataSourceAutoConfiguration.class})
public class TestApplication {
    public static void main(String[] agrs) {
        SpringApplication.run(TestApplication.class, agrs);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
