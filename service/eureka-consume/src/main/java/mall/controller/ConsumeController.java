package mall.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wang.honglin on 2018/8/30.
 */
@RestController
public class ConsumeController {
    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    // 断路器，如果出现服务A宕机等情况，执行的回掉方法，否则会出现异常。
    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping("/hello")
    public String getName() {
        return restTemplate.getForEntity("http://eureka-provider/hello", String.class).getBody();
    }

    public String error() {
        return "error";
    }
}
