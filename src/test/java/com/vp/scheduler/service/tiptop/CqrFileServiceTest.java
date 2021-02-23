package com.vp.scheduler.service.tiptop;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vp.scheduler.dao.tiptop.twvp.CqrFileRepository;
import com.vp.scheduler.entity.tiptop.twmd.MdCqrFile;
import com.vp.scheduler.servce.tiptop.twvp.CqrFileService;
import com.vp.scheduler.vo.CqrFileVo;

@SpringBootTest
class CqrFileServiceTest {

	@Autowired
	CqrFileService service;
	
	@Autowired
	CqrFileRepository dao;

	@Test
	void test() {

		System.out.println(service.getCqrFileByToday().size());
	}

	@Test
	void testOtherScm() {
		Date date = new Date();
		Instant inst = date.toInstant();
		LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
		Instant dayInst = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
		Date day = Date.from(dayInst);
		System.out.println(dao.findByCqr02OrderByCqr01Desc(day,CqrFileVo.class).size());
	}

}
