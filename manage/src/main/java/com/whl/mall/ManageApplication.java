package com.whl.mall;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;

@SpringBootApplication
// 排除不需要扫描的注解类型
@ComponentScan(excludeFilters =
		{@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Configuration.class})})
// 排除默认配置类，有些自动配置类会报错
@EnableAutoConfiguration(exclude = {MybatisAutoConfiguration.class})
public class ManageApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManageApplication.class, args);
	}
}
