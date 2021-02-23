package com.vp.scheduler.servce.faceclockin;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vp.scheduler.dao.faceclockin.FDAccLogRepository;
import com.vp.scheduler.dto.faceclockin.MemberInfo;
import com.vp.scheduler.dto.t100.T100Dto;
import com.vp.scheduler.entity.faceclockin.FDAccessLog;
import com.vp.scheduler.servce.tiptop.twvp.CpfFileService;
import com.vp.scheduler.servce.tiptop.twvp.GemFileService;
import com.vp.scheduler.singleton.EmpInfo;

/**
 * 
 * @ClassName: FDAccLogService
 * @Description: TWVP 取得人臉機打卡資料
 * @author ytc
 * @date 2021年1月12日 下午3:25:26
 *
 */
@Service
public class FDAccLogService {
	@Autowired
	FDAccLogRepository dao;

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	CpfFileService cpfFileService;
	@Autowired
	GemFileService gemFileService;

	public static FDAccLogService thisClass;

	@PostConstruct
	public void init() {
		thisClass = this;
	}

	private String getTodayDateString() {
		String dateStr = "";
		Date date = new Date();

		DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

		try {

			dateStr = sdf2.format(date);
			System.out.println(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateStr;
	}

	private String getDateToString(Date day) {
		String dateStr = "";

		DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

		try {

			dateStr = sdf2.format(day);
			System.out.println(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateStr;
	}

	public List<FDAccessLog> getCqrFileListByDate(Date day) {

		return thisClass.dao.findByCreatedAtStartingWith(getDateToString(day));
	}

	private List<FDAccessLog> getListByToday() {
		return thisClass.dao.findByCreatedAtStartingWith(getTodayDateString());
	}

	public List<T100Dto> getT100DtoList(List<FDAccessLog> list) {

		Gson gson = new Gson();
		EmpInfo empInfo = EmpInfo.getInstance(thisClass.cpfFileService.findAll(), thisClass.gemFileService.findAll());
		Map<String, String> empIdAndDepIdMap = empInfo.getEmpIdAndDepIdMap();
		Map<String, String> depIdAndDepNameMap = empInfo.getDepIdAndDepNameMap();

		List<T100Dto> t100list = new ArrayList<>();
		list.forEach((i) -> {

			try {
				JSONObject json = gson.fromJson(i.getData(), JSONObject.class);

				MemberInfo data = gson.fromJson(json.get("member_info").toString(), MemberInfo.class);

				// 設定日期格式
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				String depId = empIdAndDepIdMap.get(data.getMember_id());
				String depName = depIdAndDepNameMap.get(depId);
				if (data.getMember_id() != null) {
					t100list.add(new T100Dto(data.getMember_id(), data.getName(), depId, depName,
							sdf.parse(i.getCreatedAt()), i.getCreatedAt().split(" ")[1], data.getMeal(),
							data.getGroup_name(), "NEW_FACE"));
				}

			} catch (java.text.ParseException e) {

				e.printStackTrace();
			}

		});
		return t100list;

	}

	public Map<String, T100Dto> getTodayMap() {

		return getT100DtoList(getListByToday()).stream()
				.collect(Collectors.toMap(T100Dto::getEmpId, java.util.function.Function.identity(), (o1, o2) -> {
					Timestamp ts1 = new Timestamp(System.currentTimeMillis());
					Timestamp ts2 = new Timestamp(System.currentTimeMillis());
					String o1dt = "2020-01-01 " + o1.getClockinTime();
					String o2dt = "2020-01-01 " + o2.getClockinTime();
					try {
						System.out.println("========>" + o1dt);

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

//		map.forEach((k, i) -> {
//			System.out.println(i.getEmpId() + " " + i.getEmpName() + " " + i.getDepId() + " " + i.getDepName() + " "
//					+ sdf2.format(i.getClockinDate()) + " " + i.getClockinTime() + " " + i.getMealCode() + " "
//					+ i.getCpyId());
//		});

	}

}
