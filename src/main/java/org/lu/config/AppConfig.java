package org.lu.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { DataBaseConfig.class, CustomizedRestMvcConfiguration.class })
public class AppConfig {
	@PostConstruct
	public void initialize() {
		System.out.println("Base app setup is up");
	}

}
