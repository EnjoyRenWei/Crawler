package com.kxcbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kxcbs.dao")
public class Application {
	public static void main(String[] args) {
		//ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		//new SpringContextUtil().setApplicationContext(applicationContext);
		SpringApplication application = new SpringApplication(Application.class);
		application.run(args); 
	}
}
