package com.vp.scheduler.vo;

public interface CpfFileVo {
	// 可用target組裝拼裝結果
	// @Value("#{target.firstName + ' ' + target.lastName}")
	String getCpf01();

	String getCpf02();

	String getCpf29();

	GemFileVo getDepinfo();

}
