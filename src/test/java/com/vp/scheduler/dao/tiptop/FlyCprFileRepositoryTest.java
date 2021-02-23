package com.vp.scheduler.dao.tiptop;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vp.scheduler.dao.tiptop.twfly.FlyCqrFileRepository;
import com.vp.scheduler.entity.tiptop.twfly.FlyCqrFile;
import com.vp.scheduler.entity.tiptop.twmd.MdCqrFile;

@SpringBootTest
class FlyCprFileRepositoryTest {

	@Autowired
	FlyCqrFileRepository dao;

	@Test
	void test() {

		Date date = new Date();
		Instant inst = date.toInstant();
		LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
		Instant dayInst = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
		Date day = Date.from(dayInst);
		List<FlyCqrFile> list = (List<FlyCqrFile>) dao.findByCqr02OrderByCqr01Desc(day, FlyCqrFile.class);
		System.out.println(list.get(0).getCqr01());
	}

}
