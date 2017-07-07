package com.wujun.learning.config;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableTransactionManagement  由springboot默认支持声明式事务,可不加此注释
public class MBConfig{// implements EnvironmentAware{

//    @Override
//    public void setEnvironment(Environment environment) {
//        this.env = environment;
//    }
//
//    @Autowired
//	private Environment env;

    /**
     *
     * 因为application.properties里有spring.datasource相关配置,
     * 以下几个bean交由springboot自动生成,需要注意mybatis的MapperLocations配置
     *
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws IOException {
		System.out.println("init mb sqlSessionFactory!");
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		Interceptor[] interceptors = new Interceptor[1];
		interceptors[0] = new MBInterceptor();
		bean.setPlugins(interceptors);

		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		bean.setMapperLocations(resolver.getResources("classpath:mapper*//*.xml"));
		try {
			return bean.getObject();
		} catch (Exception e) {
			System.out.println("Msg : " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Bean
	public DataSource druidDataSource(){
        System.out.println("Get datasource....");
        DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(env.getProperty("hibernate.dialect"));
		druidDataSource.setUrl(env.getProperty("spring.datasource.url"));
		druidDataSource.setUsername(env.getProperty("spring.datasource.username"));
		druidDataSource.setPassword(env.getProperty("spring.datasource.password"));
        System.out.println("datasource : " + druidDataSource);
        return druidDataSource;
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	*/

//	@Bean
//	@Override
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		return new DataSourceTransactionManager(druidDataSource());
//	}

//	@Bean
//	public MapperScannerConfigurer mapperScannerConfigurer() {
//		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//		mapperScannerConfigurer.setBasePackage("com.wujun.learning.dao");
//		return mapperScannerConfigurer;
//	}
}