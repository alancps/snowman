package com.snowman.touristspot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.snowman.touristspot.config.AppProperties;


@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SnowmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnowmanApplication.class, args);
	}

}
