package com.vp.scheduler.jobs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vp.scheduler.dao.BaseDao;
import com.vp.scheduler.entity.Attributes;
import com.vp.scheduler.entity.Errors;
import com.vp.scheduler.entity.SFRes;
import com.vp.scheduler.entity.account.SFAccount;
import com.vp.scheduler.entity.account.T100Account;

import lombok.extern.log4j.Log4j2;
@Log4j2
public class AccountServiceBak {

	private static final CloseableHttpClient httpclient = HttpClients.createDefault();

	private List<T100Account> accountList = null;
	private ObjectMapper objectMapper = new ObjectMapper();

	public List<T100Account> getAccountListFromT100() {
		
		List<T100Account> urlist = null;
		
		try {
			BaseDao bd = new BaseDao("account");
			String list = bd.getDataFromT100();
			

			// 執行結果訊息
			String description = bd.getRep().getDescription();
			log.info(description);

			urlist = objectMapper.readValue(list, new TypeReference<List<T100Account>>() {
			});

		} catch (IOException e) {
			log.error(e);
		}
		this.accountList = urlist;

		return urlist;
	}

	// 將從T100取得的資料物件轉為SF用的物件
	private List<SFAccount> passT100ObjToSFObj() {
		List<SFAccount> ret = new ArrayList<SFAccount>();
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
			sfa.setParentid(null);
			sfa.setPaymentterms__c(account.getPaymentterms__c());
			sfa.setPhone(account.getPhone());
			sfa.setRating(account.getRating());
			sfa.setShippingaddress1__c(account.getShippingaddress1__c());
			sfa.setWebsite(account.getWebsite());
			sfa.setAttributes(new Attributes("Account", i + account.getAccount_name() + ""));
			ret.add(sfa);

			i++;
		}
		return ret;
	}

	public void insertAccountListIntoSF(String token) {
		this.getAccountListFromT100();
		List<SFAccount> sfAccountList = this.passT100ObjToSFObj();

		
		int allSize = sfAccountList.size();
		int teamNum = 50;
		// 組數
		int teamSize = allSize / teamNum;

		// 餘數
		int lastNum = allSize % teamNum;
		log.debug(allSize);
		log.debug("teamSize=" + teamSize);
		log.debug("lastNum=" + lastNum);
		List<SFAccount> tempList = new ArrayList<SFAccount>();
		int start = 0;
		for (int j = 1; j <= teamSize; j++) {
			tempList = sfAccountList.subList(start, (j * teamNum));
			String json = objListToJson(tempList);
			System.out.println("team" + j + "=" + "(" + start + "-" + (j * teamNum) + ")");
			start = start + teamNum;
			String res = doInsert(json, token);
			try {
				SFRes[] aalist = this.objectMapper.readValue(res, SFRes[].class);
				for (SFRes sfRes : aalist) {
					System.out.println(sfRes.isSuccess());
					Errors[] errs = sfRes.getErrors();
					for (Errors err : errs) {
						log.error(err.getMessage());
					}
					
				}
			} catch (JsonProcessingException e) {
				log.error(e);
			}

		}
		if (lastNum > 0) {
			tempList = sfAccountList.subList(start, allSize);
			String json = objListToJson(tempList);

			System.out.println("team" + (teamSize + 1) + "=" + "(" + start + "-" + allSize + ")");
			String res = doInsert(json, token);

		}

	}

	private String objListToJson(List list) {
		String ujson = "";
		try {
			ujson = "{\"allOrNone\" : false,\"records\": " + this.objectMapper.writeValueAsString(list) + "}";


		} catch (JsonProcessingException e) {

			log.error(e);
		}
		return ujson;
	}

	private String doInsert(String reqjson, String token) {
		String url = "https://cs76.salesforce.com/services/data/v50.0/composite/sobjects";
		HttpPost httppost = new HttpPost(url);
		httppost.addHeader("Content-Type", "application/json;charset=utf-8");
		httppost.addHeader("Authorization", "Bearer " + token);
		StringEntity se = new StringEntity(reqjson, "UTF-8");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		httppost.setEntity(se);
		CloseableHttpResponse response = null;
		String temp = null;

		try {
			response = httpclient.execute(httppost);

			HttpEntity entity1 = response.getEntity();
			temp = EntityUtils.toString(entity1);
			

		} catch (IOException e) {
			log.error(e);
		}

		return temp;

	}

}
