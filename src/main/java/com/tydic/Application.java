package com.tydic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={SecurityAutoConfiguration.class})
@MapperScan("com.tydic.dao")
public class Application {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
		application.run(args); 
	}

}
