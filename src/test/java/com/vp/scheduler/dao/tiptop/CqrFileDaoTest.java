package com.vp.scheduler.dao.tiptop;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vp.scheduler.dao.tiptop.twvp.CqrFileRepository;
import com.vp.scheduler.entity.tiptop.twvp.CqrFile;
import com.vp.scheduler.vo.CqrFileVo;

@SpringBootTest
public class CqrFileDaoTest {
	@Autowired
	CqrFileRepository dao;

	@Test
	void test() {
		
		System.out.println(dao.findByDate("2020-12-25").get(508).getCqr01());
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
//		ArrayList<CqrFileVo> newList = dao.findByCqr02Between(  new Date(2020,12,1,0,0,0),new Date(2020,1,1,0,0,0), CqrFileVo.class).stream()
//				.collect(Collectors.toCollection(ArrayList::new));
		 Date date = new Date();
		    Instant inst = date.toInstant();
		    LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
		    Instant dayInst = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
		    Date day = Date.from(dayInst);
		List<CqrFile> newList = dao.findByCqr02Between(day,day);
		
		System.out.println(newList.size());
		System.out.println(day);
		// System.out.print((dao.findByCqr02("2020-12-25",CqrFileVo.class)).toArray()..get(508).getCqrno1());
		// assertEquals(dao.count(), 1986448);
	}
	
	@Test
	void findByCqr02Test() {
		Date date = new Date();
	    Instant inst = date.toInstant();
	    LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
	    Instant dayInst = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
	    Date day = Date.from(dayInst);
		
		
		ArrayList<CqrFileVo> newList = dao.findByCqr02OrderByCqr01Desc( day, CqrFileVo.class).stream()
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(newList.get(0).getCqr01());
		
	}
	
	
}
