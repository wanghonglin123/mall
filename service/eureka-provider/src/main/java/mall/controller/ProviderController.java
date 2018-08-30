package mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wang.honglin on 2018/8/30.
 */
@RestController
public class ProviderController {
    @Autowired
    private EurekaDiscoveryClient discoveryClient;

    @RequestMapping("/hello")
    public String getName() {
        System.out.println(discoveryClient.getServices());
        return "test";
    }
}
