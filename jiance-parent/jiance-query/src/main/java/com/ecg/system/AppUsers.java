package com.ecg.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("/com.ecg.system.mapper")
public class AppUsers {
	public static void main(String[] args) {
		SpringApplication.run(AppUsers.class, args);
	}
}
