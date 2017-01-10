package com.wujun.learning.job;

import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class TestJob {
	
	@Scheduled(cron = "0 0/5 * * * *")
	public void printTime(){
		System.out.println(new Date().toLocaleString());
	}
}
