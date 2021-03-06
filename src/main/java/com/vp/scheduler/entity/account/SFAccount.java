package com.vp.scheduler.entity.account;

import com.vp.scheduler.entity.Attributes;

import lombok.Data;

@Data
public class SFAccount {

	Attributes attributes;
	String accountno__c;
	String name;
	String parentid;
	String accountsource;
	String accounttype__c;
	String rating;
	String paymentterms__c;
	String phone;
	String fax;
	String website;
	String billingaddress1__c;
	String shippingaddress1__c;
}
