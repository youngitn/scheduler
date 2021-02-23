package com.vp.scheduler.jobs;

import org.springframework.scheduling.annotation.Scheduled;

import com.vp.scheduler.servce.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountSyncTask {

	AccountService accountService = new AccountService();
	SalesForceOAuthService sfoService = new SalesForceOAuthService();

	@Scheduled(cron = "40 23 17 0 * * ?")
	public void getAccountList() {

//		//取得T100 帳號資料
//		List<Account> accountList = accountService.getAccountListFromT100();

		// 取得SF TOKEN
		String token = sfoService.getToken();
		log.info("開始同步T100&SF ACCOUNT");
		// 使用TOKEN新增SF ACCOUNT DATA
		accountService.insertListIntoSF(token);
		
		log.info("同步T100&SF ACCOUNT 結束");

	}

	public static void main(String[] avg) {

		AccountService accountService = new AccountService();
		SalesForceOAuthService sfoService = new SalesForceOAuthService();
		// 取得SF TOKEN
		String token = sfoService.getToken();
		// System.out.println("*********************>>>"+token);
		accountService.insertListIntoSF(token);
	}
}
