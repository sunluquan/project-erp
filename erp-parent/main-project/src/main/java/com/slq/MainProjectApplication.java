package com.slq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.slq.*"})
//@EnableTransactionManagement
///@ComponentScan(basePackages = {"com.slq.*"})
//@ComponentScan(basePackages={"com.slq.dao.*","com.slq.controller.*","com.slq.service.*"})
//@MapperScan(basePackages = {"com.slq.mapper.*"})
public class MainProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainProjectApplication.class, args);
	}
}
