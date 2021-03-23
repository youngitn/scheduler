package com.vp.scheduler.job;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.vp.scheduler.servce.hr.ClockinDataExporttxtService;
import com.vp.scheduler.util.ApplicationContextUtil;

@SpringBootTest
public class jobTest {

	@Test
	void test() throws ParseException, IOException {
		
		DateTime  ld =new DateTime ();
		ld.plusDays(1);
		System.out.print(ld.plusDays(-1).toDate());
		
		System.out.println("GO");
		Date date = new Date();
//
//		// 欲轉換的日期字串
		String dateString = "2021-03-21";
		// 設定日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 進行轉換
		date = sdf.parse(dateString);
		ClockinDataExporttxtService service = (ClockinDataExporttxtService)ApplicationContextUtil.getBean("ClockinDataExporttxtService");
		String allStr = "";
		
		////allStr += service.getStrByDate(sdf.parse(ld.toString("yyyy-MM-dd")));
		allStr += service.getStrByDate(date);
		////allStr += service.getStrByDate(sdf.parse(ld.plusDays(-1).toString("yyyy-MM-dd")));
		////service.write(allStr,ld.toString("yyyyMMdd"));
		service.write(allStr,ld.toString("20210321"));
		//service.run(sdf.parse(ld.plusDays(-1).toString("yyyy-MM-dd")));
	}
}
