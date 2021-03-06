package com.github.dreamroute.deep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 1、研究各种注解
 * 2、研究SpringBoot源码
 */
@SpringBootApplication
@EnableJpaRepositories
@MapperScan("com.github.dreamroute.deep.mapper")
public class DeepApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeepApplication.class, args);
	}

}

