package com.wujun.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAuthorizationServer
@EnableResourceServer
public class LearningApplication {

	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication(LearningApplication.class);
		sa.setAdditionalProfiles("dev");
		sa.run(args);
	}

//	@Bean
//	public FilterRegistrationBean get() {
//		FilterRegistrationBean filter = new FilterRegistrationBean();
//
//		filter.setName("greeting");
//		GreetingFilter greetingFilter = new GreetingFilter();
//		filter.setFilter(greetingFilter);
//		filter.setOrder(1);
//		List<String> urlList = new ArrayList<String>();
//		urlList.add("/common/*");
//		filter.setUrlPatterns(urlList);
//
//		return filter;
//	}

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfoBuilder().title("Learning boot!").description("My learning web!")
						.termsOfServiceUrl("url").version("2.0").build())
				.select().apis(RequestHandlerSelectors.basePackage("com.wujun.learning.api")).paths(PathSelectors.any())
				.build();
	}

}
