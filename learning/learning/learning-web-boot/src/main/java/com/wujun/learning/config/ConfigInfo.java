package com.wujun.learning.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class ConfigInfo {
	@Value("${key1}")
	private String key;
	
	@Value("${profilekey}")
	private String profileKey;
	
	@Value("${homekey}")
	private String homeKey;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getHomeKey() {
		return homeKey;
	}

	public void setHomeKey(String homeKey) {
		this.homeKey = homeKey;
	}

	public String getProfileKey() {
		return profileKey;
	}

	public void setProfileKey(String profileKey) {
		this.profileKey = profileKey;
	}

}
