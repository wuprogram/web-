package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.example")
@SpringBootApplication
@ComponentScan
public class DemoTest2Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoTest2Application.class, args);
	}

}
