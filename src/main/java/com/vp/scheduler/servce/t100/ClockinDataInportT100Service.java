package com.vp.scheduler.servce.t100;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.scheduler.dao.t100.OrdermealucRepository;
import com.vp.scheduler.dto.t100.T100Dto;
import com.vp.scheduler.entity.t100.Ordermealuc_t;
import com.vp.scheduler.servce.faceclockin.FDAccLogService;
import com.vp.scheduler.servce.tiptop.twfly.FlyClockinService;
import com.vp.scheduler.servce.tiptop.twmd.MdClockinService;
import com.vp.scheduler.servce.tiptop.twvp.ClockinService;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service("ClockinDataInportT100Service")
public class ClockinDataInportT100Service {

	

	@Autowired
	OrdermealucRepository ordermealucDao;

	public static ClockinDataInportT100Service thisClass;

	@PostConstruct
	public void init() {
		thisClass = this;
	}
	
	public void run() {
		log.info("開始進行訂餐資料匯入T100動作>>>>>>>>>>>>>>>>>>>");
		/// 彰濱
		FlyClockinService flyService = new FlyClockinService();

		/// 墨達思
		MdClockinService mdService = new MdClockinService();

		/// TWVP
		ClockinService vpService = new ClockinService();

		/// 人臉辨識
		FDAccLogService fdaService = new FDAccLogService();

		List<Ordermealuc_t> ordermealuc_tList = new ArrayList<>();
		List<T100Dto> t100DtoList = new ArrayList<>();

		t100DtoList.addAll(toList(vpService.getClockinMap()));
		t100DtoList.addAll(toList(flyService.getClockinMap()));
		t100DtoList.addAll(toList(mdService.getClockinMap()));
		t100DtoList.addAll(toList(fdaService.getTodayMap()));

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int i = 0;
		t100DtoList.forEach((v) -> {

//			System.out.println(v.getEmpId() + " " + v.getEmpName() + " " + v.getDepId() + " " + v.getDepName() + " "
//					+ sdf.format(v.getClockinDate()) + " " + v.getClockinTime() + " " + v.getMealCode() + " "
//					+ v.getCpyId() + " " + v.getSource());

			Timestamp clockinDatetime = null;
			String sec = "";
			if (!"NEW_FACE".equals(v.getSource())) {
				sec = ":00";
			}
			clockinDatetime = Timestamp.valueOf(sdf.format(v.getClockinDate()) + " " + v.getClockinTime() + sec);
			ordermealuc_tList.add(new Ordermealuc_t(100, v.getCpyId(), v.getEmpId(), v.getEmpName(), v.getDepId(),
					v.getDepName(), clockinDatetime, clockinDatetime, v.getMealCode(), v.getSource()));

//			thisClass.ordermealucDao.save(new Ordermealuc_t(100, v.getCpyId(), v.getEmpId(), v.getEmpName(), v.getDepId(), v.getDepName(),
//					clockinDatetime,
//					clockinDatetime,
//					v.getMealCode(), v.getSource()));

		});

		ordermealuc_tList.forEach((v) -> {
			System.out.println(v.getOrdermealucent() + " " + v.getOrdermealucsite() + " " + v.getOrdermealuc001() + " "
					+ v.getOrdermealuc002() + " " + v.getOrdermealuc003() + " " + v.getOrdermealuc004() + " "
					+ v.getOrdermealuc005() + " " + v.getOrdermealuc006() + " " + v.getOrdermealuc007() + " "
					+ v.getOrdermealuc008());
			thisClass.ordermealucDao.save(v);
		});

//		if (ordermealuc_tList.size() > 0) {
//			thisClass.ordermealucDao.saveAll(ordermealuc_tList);
//			ordermealuc_tList.clear();
//        }
		
		log.info("結束進行訂餐資料匯入T100動作<<<<<<<<<<<<<<<<<<<<<");

	}

	public List<T100Dto> toList(Map<String, T100Dto> map) {
		return map.entrySet().stream().map(Entry::getValue).collect(Collectors.toList());
	}
	
	
}
