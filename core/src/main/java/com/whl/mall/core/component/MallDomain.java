package com.whl.mall.core.component;
/**
 * @Title: MallDomain
 * @Package: com.whl.mall.common.component
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-12 下午 10:46
 * @Version: V2.0.0
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MallDomain
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-12 下午 10:46
 */
@Component
public class MallDomain {
    @Value("${spring.profiles.active}")
    private String env;

    public String getDomain() {
        return env;
    }
}
