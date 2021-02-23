package com.vp.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.vp.scheduler.servce.t100.ClockinDataInportT100Service;

@SpringBootTest
public class ClockinDataInportT100Test {
	@Test
	void testRun() {
		ClockinDataInportT100Service task = new ClockinDataInportT100Service();
		task.run();
		
	}

}
