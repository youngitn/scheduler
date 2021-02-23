package com.vp.scheduler.dao.tiptop;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vp.scheduler.dao.tiptop.twmd.MdCqrFileRepository;
import com.vp.scheduler.entity.tiptop.twmd.MdCqrFile;

@SpringBootTest
class MdCprFileRepositoryTest {

	@Autowired
	MdCqrFileRepository dao;

	@Test
	void test() {

		Date date = new Date();
		Instant inst = date.toInstant();
		LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
		Instant dayInst = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
		Date day = Date.from(dayInst);
		List<MdCqrFile> list = (List<MdCqrFile>) dao.findByCqr02OrderByCqr01Desc(day, MdCqrFile.class);
		System.out.println(list.get(0).getCqr01());
	}

}
