package com.vp.scheduler.jobs;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;

import com.vp.scheduler.servce.hr.ClockinDataExporttxtService;
import com.vp.scheduler.servce.t100.ClockinDataInportT100Service;
import com.vp.scheduler.util.ApplicationContextUtil;
/**
 * 
* @ClassName: ClockinTask 
* @Description: 來自tiptop&人臉機的打卡資料處理 
* @author ytc
* @date 2021年2月18日 上午7:51:10 
*
 */
@PropertySource(value = "file:./some.properties", encoding = "utf-8")
public class ClockinTask {

	@Value("${test.priority}")
	public static String priority;

	/**
	 * 
	* @Title: go 
	* @Description: tiptop(所有公司)打卡資料匯入t100 table 
	* @param     設定檔案 
	* @return void    返回型別 
	* @throws
	 */
	@Scheduled(cron = "0 55 8 * * ?")
	public void importClockinDataToT100Table() {
		System.out.println("GO");
		ClockinDataInportT100Service service = (ClockinDataInportT100Service) ApplicationContextUtil
				.getBean("ClockinDataInportT100Service");
		service.run();

	}

	/**
	 * 
	* @Title: go2 
	* @Description: tiptop+人臉打卡資料匯出成txt,堤供HR匯入用 
	* @param @throws IOException
	* @param @throws ParseException
	* @param @throws InterruptedException    設定檔案 
	* @return void    返回型別 
	* @throws
	 */
	@Scheduled(cron = "0 55 8 * * ?")
	public void importClockinDataToHRsys() throws IOException, ParseException, InterruptedException {
		System.out.println("importClockinDataToHRsys");
		DateTime ld = new DateTime();

		// 欲轉換的日期字串
		// String dateString = "2021-01-13";
		// 設定日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 進行轉換
		// date = sdf.parse(dateString);
		ClockinDataExporttxtService service = (ClockinDataExporttxtService) ApplicationContextUtil
				.getBean("ClockinDataExporttxtService");
		String allStr = "";

//		allStr += service.getStrByDate(sdf.parse(ld.toString("yyyy-MM-dd")));
//		allStr += service.getStrByDate(sdf.parse(ld.plusDays(-1).toString("yyyy-MM-dd")));
//		service.write(allStr, ld.toString("yyyyMMdd"));
		
		//today
		allStr = service.getStrByDate(sdf.parse(ld.toString("yyyy-MM-dd")));
		service.write(allStr, ld.toString("yyyyMMdd"));
		//yesterday
		allStr = service.getStrByDate(sdf.parse(ld.plusDays(-1).toString("yyyy-MM-dd")));
		service.write(allStr, ld.plusDays(-1).toString("yyyyMMdd"));
	}

	public static void main(String[] a) throws IOException, ParseException {

		Date date = new Date();

		// 欲轉換的日期字串
		String dateString = "2021-01-13";
		// 設定日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 進行轉換
		date = sdf.parse(dateString);
		System.out.println(date);

	}
}
