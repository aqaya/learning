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
	
//	my.secret=${random.value}
//	my.number=${random.int}
//	my.bignumber=${random.long}
//	my.uuid=${random.uuid}
//	my.number.less.than.ten=${random.int(10)}
//	my.number.in.range=${random.int[1024,65536]}
	
	@Value("${my.secret}")
	private String mySecret;
	@Value("${my.number}")
	private int myNumber;
	@Value("${my.bignumber}")
	private long myBigNumber;
	@Value("${my.uuid}")
	private String myUuid;
	@Value("${my.number.less.than.ten}")
	private int myNumberLessThan10;
	@Value("${my.number.in.range}")
	private int myNumberInRange1024to65536;
	
	
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

	@Override
	public String toString() {
		return "ConfigInfo [key=" + key + ", profileKey=" + profileKey + ", homeKey=" + homeKey + ", mySecret="
				+ mySecret + ", myNumber=" + myNumber + ", myBigNumber=" + myBigNumber + ", myUuid=" + myUuid
				+ ", myNumberLessThan10=" + myNumberLessThan10 + ", myNumberInRange1024to65536="
				+ myNumberInRange1024to65536 + "]";
	}

}
