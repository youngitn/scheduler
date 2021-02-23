package com.vp.scheduler.vo;

import java.util.Date;

public interface CqrFileVo {
	//可用target組裝拼裝結果
	//@Value("#{target.firstName + ' ' + target.lastName}")
	String getCqr01();

	Date getCqr02();

	String getCqr03();

	String getCqrno1();

	String getCqrno2();
}
