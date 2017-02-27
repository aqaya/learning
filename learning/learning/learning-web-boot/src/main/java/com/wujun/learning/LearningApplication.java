package com.wujun.learning;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@EnableAuthorizationServer
//@EnableResourceServer
public class LearningApplication {

	@Autowired
    private Environment env;
	
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
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);

        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql", "true"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql", "true"));
        properties.put("hibernate.jdbc.batch_size", env.getProperty("hibernate.jdbc.batch_size", "30"));
        properties.put("hibernate.current_session_context_class", env.getProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto", "update"));
        properties.put("hibernate.id.new_generator_mappings", env.getProperty("hibernate.id.new_generator_mappings", "false"));
        properties.put("hibernate.default_schema", env.getProperty("hibernate.default_schema", "elearning"));
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setPackagesToScan(env.getProperty("hibernate.package.scan", this.getClass().getPackage().getName()));

        return localSessionFactoryBean;
    }

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfoBuilder().title("Learning boot!").description("My learning web!")
						.termsOfServiceUrl("url").version("2.0").build())
				.select().apis(RequestHandlerSelectors.basePackage("com.wujun.learning.api")).paths(PathSelectors.any())
				.build();
	}

}
