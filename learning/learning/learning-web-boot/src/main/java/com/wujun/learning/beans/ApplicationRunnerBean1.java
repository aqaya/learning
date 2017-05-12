package com.wujun.learning.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.wujun.learning.config.ConfigInfo;
import com.wujun.learning.config.FooProperties;

//@ConfigurationProperties("foo")
@Component
public class ApplicationRunnerBean1 implements ApplicationRunner {

	@Autowired
    ConfigInfo configInfo;
	
	@Autowired
	FooProperties fooProperties;
	
	@Value("${key1}")
	private String key;
	
//	@Value("${keyaddition}")
//	private String keyaddition;
	
	//@Value("${profilekey}")
	private String profileKey;
	
	@Value("${homekey}")
	private String homeKey;
	
	@Value("${kkk}")
	private String kkk;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(this.toString());
	}

	public ConfigInfo getConfigInfo() {
		return configInfo;
	}

	public void setConfigInfo(ConfigInfo configInfo) {
		this.configInfo = configInfo;
	}

	public FooProperties getFooProperties() {
		return fooProperties;
	}

	public void setFooProperties(FooProperties fooProperties) {
		this.fooProperties = fooProperties;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

//	public String getKeyaddition() {
//		return keyaddition;
//	}
//
//	public void setKeyaddition(String keyaddition) {
//		this.keyaddition = keyaddition;
//	}

	public String getProfileKey() {
		return profileKey;
	}

	public void setProfileKey(String profileKey) {
		this.profileKey = profileKey;
	}

	public String getHomeKey() {
		return homeKey;
	}

	public void setHomeKey(String homeKey) {
		this.homeKey = homeKey;
	}

	public String getKkk() {
		return kkk;
	}

	public void setKkk(String kkk) {
		this.kkk = kkk;
	}

}
