package com.github.dreamroute.deep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 1、研究各种注解
 * 2、研究SpringBoot源码
 */
@EnableJpaRepositories
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DeepApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeepApplication.class, args);
	}

}

