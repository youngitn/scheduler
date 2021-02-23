package com.vp.scheduler.entity.quote;

import com.vp.scheduler.entity.Attributes;

import lombok.Data;

@Data
public class T100Quote {
	
	Attributes attributes;
	String quoteid;
	String opportunityid;
	String pricebook2id;
	String quoteno__c;
	String quote_name;
	String currencyisocode;
	String incoterm__c;
	String tax__c;
	String paymentterms__c;
	String effectivedate__c;
	String quotedate__c;
	String customerid__c;
	String sales__c;
}
