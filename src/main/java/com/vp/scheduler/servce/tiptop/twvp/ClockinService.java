package com.vp.scheduler.servce.tiptop.twvp;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.scheduler.dto.t100.T100Dto;
import com.vp.scheduler.servce.t100.ClockinDataIntegrationService;

@Service
public class ClockinService {
	@Autowired
	ClockinDataIntegrationService service;

	@Autowired
	CqrFileService cqrService;
	public static ClockinService thisClass;

	@PostConstruct
	public void init() {
		thisClass = this;
	}

	public Map<String, T100Dto> getClockinMap() {

		//打卡資料進入點
		thisClass.service.setList(thisClass.cqrService.getCqrFileByToday());
		List<T100Dto> list = thisClass.service.getMappedList();

		System.out.println(list.size());

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
//		map.forEach((key, item) -> {
//			System.out.println(key + " " + item.getEmpName() + " " + item.getDepId() + " " + item.getDepName() + " "
//					+ sdfd.format(item.getClockinDate()) + " " + item.getClockinTime() + " " + item.getMealCode()
//					+ " ");
//		});

		return map;

	}
}
