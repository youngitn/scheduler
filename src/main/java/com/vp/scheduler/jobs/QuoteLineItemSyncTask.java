package com.vp.scheduler.jobs;

import org.springframework.scheduling.annotation.Scheduled;

import com.vp.scheduler.servce.QuoteLineItemService;
import com.vp.scheduler.servce.QuoteService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class QuoteLineItemSyncTask {

	QuoteLineItemService quoteLineItemService = new QuoteLineItemService();
	SalesForceOAuthService sfoService = new SalesForceOAuthService();

	@Scheduled(cron = "0 0 0 * * ?")
	public void getQuoteLineItemList() {

		// 取得SF TOKEN
		String token = sfoService.getToken();
		log.info("開始同步T100&SF quoteLineItem");
		// 使用TOKEN新增SF ACCOUNT DATA
		quoteLineItemService.insertListIntoSF(token);

		log.info("同步T100&SF quoteLineItem 結束");

	}

	public static void main(String[] avg) {

		QuoteLineItemService accountService = new QuoteLineItemService();
		SalesForceOAuthService sfoService = new SalesForceOAuthService();
		// 取得SF TOKEN
		String token = sfoService.getToken();
		// System.out.println("*********************>>>"+token);
		accountService.insertListIntoSF(token);
	}
}
