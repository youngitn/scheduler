package com.vp.scheduler.entity.t100;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdermealucKeys implements Serializable {

	/// 據點
	private String ordermealucsite;

	/// 工號
	private String ordermealuc001;

	/// 訂餐資料來源
	private String ordermealuc008;
	
	///打卡日期
	private Timestamp ordermealuc005;
}
