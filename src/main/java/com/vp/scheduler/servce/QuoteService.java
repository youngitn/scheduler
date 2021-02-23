package com.vp.scheduler.servce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vp.scheduler.dao.BaseDao;
import com.vp.scheduler.entity.Attributes;
import com.vp.scheduler.entity.quote.SFQuote;
import com.vp.scheduler.entity.quote.T100Quote;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class QuoteService extends BaseService {

	List<T100Quote> quoteList = null;

	@Override
	List<T100Quote> getListFromT100() {
		List<T100Quote> urlist = null;

		try {
			BaseDao bd = new BaseDao("quote");
			String list = bd.getDataFromT100();

			// 執行結果訊息
			String description = bd.getRep().getDescription();
			log.info(description);

			urlist = objectMapper.readValue(list, new TypeReference<List<T100Quote>>() {
			});
			this.quoteList = urlist;

		} catch (IOException e) {
			log.error(e);
		}

		return urlist;
	}

	@Override
	List<SFQuote> passT100ObjToSFObj() {
		List<SFQuote> ret = new ArrayList<>();
		// 從0開始 注意後續分段取list也是0開始
		int i = 0;
		for (T100Quote item : quoteList) {
			SFQuote sfa = new SFQuote();
			sfa.setQuoteno__c(item.getQuoteno__c());
			sfa.setName(item.getQuote_name());
			sfa.setCurrencyisocode(item.getCurrencyisocode());
			sfa.setIncoterm__c(item.getIncoterm__c());
			sfa.setTax__c(item.getTax__c());
			sfa.setPaymentterms__c(item.getPaymentterms__c());
			sfa.setEffectivedate__c(item.getEffectivedate__c());
			sfa.setQuotedate__c(item.getQuotedate__c());
			sfa.setCustomerid__c(item.getCustomerid__c());
			sfa.setSales__c(item.getSales__c());
			sfa.setOpportunityId("");
			sfa.setAttributes(new Attributes("Quote", i + item.getQuoteid() + ""));
			ret.add(sfa);

			i++;
		}
		return ret;
	}

	@Override
	List<SFQuote> resetListType(List list) {
		List<SFQuote> ret = list;
		return ret;
	}

	@Override
	Attributes getAttributes(Object obj) {
		SFQuote ret = (SFQuote) obj;
		return ret.getAttributes();
	}

}
