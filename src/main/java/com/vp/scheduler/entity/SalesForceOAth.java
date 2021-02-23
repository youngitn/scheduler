package com.vp.scheduler.entity;

import lombok.Data;

@Data
public class SalesForceOAth {
	String access_token;
	String instance_url;
	String id;
	String token_type;
	String issued_at;
	String signature;
}
