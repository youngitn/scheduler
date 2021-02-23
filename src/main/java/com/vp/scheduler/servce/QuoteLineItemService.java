package com.vp.scheduler.servce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vp.scheduler.dao.BaseDao;
import com.vp.scheduler.entity.Attributes;
import com.vp.scheduler.entity.quiteLineItem.SFQuoteLineItem;
import com.vp.scheduler.entity.quiteLineItem.T100QuoteLineItem;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class QuoteLineItemService extends BaseService {

	List<T100QuoteLineItem> quoteLineItemList = null;

	@Override
	List<T100QuoteLineItem> getListFromT100() {
		List<T100QuoteLineItem> urlist = null;

		try {
			BaseDao bd = new BaseDao("quotelineitem");
			String list = bd.getDataFromT100();

			// 執行結果訊息
			String description = bd.getRep().getDescription();
			log.info(description);

			urlist = objectMapper.readValue(list, new TypeReference<List<T100QuoteLineItem>>() {
			});
			this.quoteLineItemList = urlist;

		} catch (IOException e) {
			log.error(e);
		}

		return urlist;
	}

	@Override
	List<SFQuoteLineItem> passT100ObjToSFObj() {
		List<SFQuoteLineItem> ret = new ArrayList<>();
		// 從0開始 注意後續分段取list也是0開始
		int i = 0;
		for (T100QuoteLineItem item : quoteLineItemList) {
			SFQuoteLineItem sfa = new SFQuoteLineItem();
			sfa.setQuoteid(item.getQuoteid());
			sfa.setItemno1__c(item.getItemno1__c());
			sfa.setCurrencyisocode(item.getCurrencyisocode());
			sfa.setQuotenoitemno__c(item.getQuotenoitemno__c());
			sfa.setPricebookentryid(item.getPricebookentryid());
			sfa.setProduct2id(item.getProduct2id());
			sfa.setUnitprice(item.getUnitprice());
			sfa.setQuantity(item.getQuantity());
			sfa.setTeststandard__c(item.getTeststandard__c());
			sfa.setPtf__c(item.getPtf__c());
			sfa.setTargetprice__c(item.getTargetprice__c());
			sfa.setFobprice__c(item.getFobprice__c());
			sfa.setNtdunitprice__c(item.getNtdunitprice__c());
			sfa.setQuoteexchangerate__c(item.getQuoteexchangerate__c());
			sfa.setAnnualqty__c(item.getAnnualqty__c());
			sfa.setBrandannualqty__c(item.getBrandannualqty__c());
			sfa.setPackingremark__c(item.getPackingremark__c());
			sfa.setPacking__c(item.getPacking__c());
			sfa.setProductname__c(item.getProductname__c());
			sfa.setProductno__c(item.getProductno__c());
			sfa.setProductseries__c(item.getProductseries__c());
			sfa.setProductspec__c(item.getProductspec__c());
			sfa.setUnit__c(item.getUnit__c());
			sfa.setRemarks__c(item.getRemarks__c());
			sfa.setAttributes(new Attributes("quotelineitem", i + item.getQuoteid() + ""));
			ret.add(sfa);

			i++;
		}
		return ret;
	}

	@Override
	List<SFQuoteLineItem> resetListType(List list) {
		List<SFQuoteLineItem> ret = list;
		return ret;
	}

	@Override
	Attributes getAttributes(Object obj) {
		SFQuoteLineItem ret = (SFQuoteLineItem) obj;
		return ret.getAttributes();
	}

}
