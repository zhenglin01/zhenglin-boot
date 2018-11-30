package com.primeton.zhenglin.demo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring boot的启动程序
 * @author Lion
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.primeton.zhenglin.dao.*")//扫描：该包下相应的class，主要是MyBatic
public class ZhenglinDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZhenglinDemoApplication.class, args);
		
	}
}
