package org.lu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.format.support.FormattingConversionService;

@Configuration
public class CustomizedRestMvcConfiguration extends
		RepositoryRestMvcConfiguration {

	// Required by spring mvc. It's missing in current used spring version
	@Bean(name = "mvcConversionService")
	public ConversionService mvcConversionService() {
		return new FormattingConversionService();
	}

	@Override
	public RepositoryRestConfiguration config() {
		RepositoryRestConfiguration config = super.config();
		config.setBasePath("/api");
		return config;
	}
}
