package com.vp.scheduler.dao.tiptop;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vp.scheduler.dao.tiptop.twvp.CpfFileRepository;
import com.vp.scheduler.entity.tiptop.twvp.CpfFile;

@SpringBootTest
class CpfFileRepositoryTest {

	@Autowired
	CpfFileRepository dao;
	@Test
	void test() {
		
		List<CpfFile> list = (List<CpfFile>) dao.findAll();
		System.out.println(list.get(0).getDepinfo().getGem02());
	}

}
