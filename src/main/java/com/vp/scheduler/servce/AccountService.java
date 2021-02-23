package com.vp.scheduler.servce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vp.scheduler.dao.BaseDao;
import com.vp.scheduler.entity.Attributes;
import com.vp.scheduler.entity.account.SFAccount;
import com.vp.scheduler.entity.account.T100Account;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AccountService extends BaseService {

	List<T100Account> accountList = null;
	@Override
	List<T100Account> getListFromT100() {
		List<T100Account> urlist = null;

		try {
			BaseDao bd = new BaseDao("account");
			String list = bd.getDataFromT100();

			// 執行結果訊息
			String description = bd.getRep().getDescription();
			log.info(description);

			urlist = objectMapper.readValue(list, new TypeReference<List<T100Account>>() {
			});
			this.accountList = urlist;

		} catch (IOException e) {
			log.error(e);
		}

		return urlist;
	}

	@Override
	// 將從T100取得的資料物件轉為SF用的物件
	List<SFAccount> passT100ObjToSFObj() {
	
		List<SFAccount> ret = new ArrayList<>();
		// 從0開始 注意後續分段取list也是0開始
		int i = 0;
		for (T100Account account : accountList) {
			SFAccount sfa = new SFAccount();
			sfa.setAccountno__c(account.getAccountno__c());
			sfa.setAccountsource(account.getAccountsource());
			sfa.setAccounttype__c(account.getAccounttype__c());
			sfa.setBillingaddress1__c(account.getBillingaddress1__c());
			sfa.setFax(account.getFax());
			sfa.setName(account.getAccount_name());
			// sfa.setParentid(account.getParentid());
			//由業務上SF手動調整
			sfa.setParentid(null);
			sfa.setPaymentterms__c(account.getPaymentterms__c());
			sfa.setPhone(account.getPhone());
			sfa.setRating(account.getRating());
			sfa.setShippingaddress1__c(account.getShippingaddress1__c());
			sfa.setWebsite(account.getWebsite());
			sfa.setAttributes(new Attributes("Account", i + account.getAccount_name() + ""));
			ret.add(sfa);

			i++;
			/**
			 * 建虛擬機會物件
			 */
			
		}
		return ret;
	}

	@Override
	List<SFAccount> resetListType(List list) {
		List<SFAccount> ret = list;
		return ret;
	}

	@Override
	Attributes getAttributes(Object obj) {
		SFAccount ret = (SFAccount) obj;
		return ret.getAttributes();
	}

}
