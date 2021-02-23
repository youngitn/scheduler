package com.vp.scheduler.service.t100;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vp.scheduler.dto.t100.T100Dto;
import com.vp.scheduler.servce.t100.FlyClockinDataIntegrationService;
import com.vp.scheduler.servce.tiptop.twfly.FlyCqrFileService;

@SpringBootTest
class FlyClockinDataIntegrationServiceTest {

	@Autowired
	FlyClockinDataIntegrationService service;

	@Autowired
	FlyCqrFileService cqrService;

	@Test
	void test() {
		String dateString = "2021-01-05 00:00:00";
		// 設定日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 進行轉換
		Date date = null;
		try { 
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.setList(cqrService.getCqrFileListByDate(date));
		List<T100Dto> list = service.getMappedList();

		System.out.println(list.size());
//		Map<String, T100Dto> guavaMap = Maps.uniqueIndex(list, new com.google.common.base.Function<T100Dto, String>() {
//			@Nullable
//			@Override
//			public String apply(@Nullable T100Dto k) {
//				if (k.getEmpId() == "I1639")
//				return k.getEmpId();
//				else {
//					return null;
//				}
//			}
//		});

		Map<String, T100Dto> map = list.stream()
				.collect(Collectors.toMap(T100Dto::getEmpId, java.util.function.Function.identity(), (o1, o2) -> {
					Timestamp ts1 = new Timestamp(System.currentTimeMillis());
					Timestamp ts2 = new Timestamp(System.currentTimeMillis());
					String o1dt = "2020-01-01 " + o1.getClockinTime() + ":00";
					String o2dt = "2020-01-01 " + o2.getClockinTime() + ":00";
					try {
						// System.out.println("========>"+o1dt);
						ts1 = Timestamp.valueOf(o1dt);
						ts2 = Timestamp.valueOf(o2dt);

					} catch (Exception e) {
						e.printStackTrace();
					}
					if (ts1.compareTo(ts2) > 0) {
						return o1;
					}
					return o2;
				}));
		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
		map.forEach((key, item) -> {
			System.out.println(key + " " + item.getEmpName() + " " + item.getDepId() + " " + item.getDepName() + " "
					+ sdfd.format(item.getClockinDate()) + " " + item.getClockinTime() + " " + item.getMealCode()
					+ " ");
		});

//		list.forEach((item) -> {
//			System.out.println(item.getEmpId() + " " + item.getEmpName() + " " + item.getDepId() + " "
//					+ item.getDepName() + " " + sdfd.format(item.getClockinDate()) + " " + item.getClockinTime() + " "
//					+ item.getMealCode() + " ");
//		});
//		guavaMultiMap.forEach((key, item) -> {
//			System.out.println(key + " " + item.getEmpName() + " " + item.getDepId() + " " + item.getDepName() + " "
//					+ item.getClockinDate() + " " + item.getClockinTime() + " " + item.getMealCode() + " "
//					+ item.getClockinTime());
//		});
//		System.out.println("----------------------------------------------------------------------");
//		service.getMappedList().forEach((item) -> {
//			System.out.println(
//					item.getEmpId() + " " + item.getEmpName() + " " + item.getDepId() + " " + item.getDepName());
//		});

//		Map<String,String> map =(cpfRepository.findAll()).stream()
//		.collect(Collectors.toMap(CpfFile::getCpf01, CpfFile::getCpf02));
//		
//		map.forEach((i,k)->{
//			System.out.println(i+"  "+k);
//		});

	}

}
