package com.vp.scheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


import com.vp.scheduler.jobs.ClockinTask;

@Configuration
@EnableScheduling
public class Config {
	
		 
//	@Bean
//	public AccountSyncTask task() {
//		return new AccountSyncTask();
//	}
//	
//	@Bean
//	public QuoteSyncTask quoteSyncTask() {
//		return new QuoteSyncTask();
//	}
//	
//	@Bean
//	public QuoteLineItemSyncTask quoteLineItemSyncTask() {
//		return new QuoteLineItemSyncTask();
//	}
	
	@Bean
	public ClockinTask task2() {
		return new ClockinTask();
	}
}