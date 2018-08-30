package mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wang.honglin on 2018/8/30.
 */
@RestController
public class ConsumeController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String getName() {
        return restTemplate.getForEntity("http://eureka-provider/hello", String.class).getBody();
    }
}
