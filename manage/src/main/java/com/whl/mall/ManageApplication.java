package com.whl.mall;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
// 排除不需要扫描的注解类型
@ComponentScan(excludeFilters =
		{@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Configuration.class})})
// 排除默认配置类，有些自动配置类会报错

public class ManageApplication implements ApplicationRunner{

	private static final Logger logger = LogManager.getLogger(ManageApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ManageApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		logger.info("[{}]", "app 启动成功，正在初始化一些参数");
		logger.error("Oops! We have an Error. OK");
	}
}
