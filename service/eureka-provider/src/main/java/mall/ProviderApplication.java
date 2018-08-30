package mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.jdbc.DataSourceHealthIndicatorAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by wang.honglin on 2018/8/30.
 */
@SpringBootApplication
@EnableDiscoveryClient
// 排除不需要的spring 提供的自动配置类
@EnableAutoConfiguration(exclude = {DataSourceHealthIndicatorAutoConfiguration.class,
        DataSourceAutoConfiguration.class})
public class ProviderApplication {
    public static void main(String[] agrs) {
        SpringApplication.run(ProviderApplication.class, agrs);
    }
}
