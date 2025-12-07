package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mapper")
public class OnlineLearningApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnlineLearningApplication.class, args);
	}
}

