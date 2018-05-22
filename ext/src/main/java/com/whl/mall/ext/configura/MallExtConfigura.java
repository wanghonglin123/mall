package com.whl.mall.ext.configura;
/**
 * @Title: MallExtConfigura
 * @Package: com.whl.mall.ext.configura
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-22 下午 9:32
 * @Version: V2.0.0
 */

import com.whl.mall.pojo.member.Member;
import com.whl.mall.pojo.member.Role;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MallExtConfigura
 * @Description: 扩展配置
 * @Author: WangHongLin
 * @Date: 2018-05-22 下午 9:32
 */
@Configuration
public class MallExtConfigura {
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();
        jsonMessageConverter.setJavaTypeMapper(jackson2JavaTypeMapper());
        return jsonMessageConverter;
    }

    @Bean
    public DefaultJackson2JavaTypeMapper jackson2JavaTypeMapper() {
        DefaultJackson2JavaTypeMapper jackson2JavaTypeMapper = new DefaultJackson2JavaTypeMapper();
        //消费端配置映射
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("member",Member.class);
        idClassMapping.put("role",Role.class);

        jackson2JavaTypeMapper.setIdClassMapping(idClassMapping);
        return jackson2JavaTypeMapper;
    }
}
