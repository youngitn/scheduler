package com.vp.scheduler.jobs;

import org.springframework.scheduling.annotation.Scheduled;

import com.vp.scheduler.servce.QuoteService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class QuoteSyncTask {

	QuoteService quoteService = new QuoteService();
	SalesForceOAuthService sfoService = new SalesForceOAuthService();

	@Scheduled(cron = "0 0 0 * * ?")
	public void getQuoteList() {

		// 取得SF TOKEN
		String token = sfoService.getToken();
		log.info("開始同步T100&SF quote");
		// 使用TOKEN新增SF ACCOUNT DATA
		quoteService.insertListIntoSF(token);

		log.info("同步T100&SF quote 結束");

	}

	public static void main(String[] avg) {

		QuoteService accountService = new QuoteService();
		SalesForceOAuthService sfoService = new SalesForceOAuthService();
		// 取得SF TOKEN
		String token = sfoService.getToken();
		// System.out.println("*********************>>>"+token);
		accountService.insertListIntoSF(token);
	}
}
