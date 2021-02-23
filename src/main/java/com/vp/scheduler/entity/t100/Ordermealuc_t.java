package com.vp.scheduler.entity.t100;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(schema = "dsdata")
@IdClass(OrdermealucKeys.class)
public class Ordermealuc_t implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	/// 100
	private int ordermealucent;
	
	@Id
	/// 據點
	private String ordermealucsite;
	
	@Id
	/// 工號
	private String ordermealuc001;

	/// 姓名
	private String ordermealuc002;

	/// 部門編號
	private String ordermealuc003;
	/// 部門名稱
	private String ordermealuc004;
	
	@Id
	/// 打卡日期
	private Timestamp ordermealuc005;
	//// 打卡時間
	private Timestamp ordermealuc006;
	/// 訂餐狀態
	private String ordermealuc007;
	@Id
	/// 訂餐資料來源
	private String ordermealuc008;

}
