package com.vp.scheduler.dao.faceclockin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vp.scheduler.dto.faceclockin.MemberInfo;
import com.vp.scheduler.dto.t100.T100Dto;
import com.vp.scheduler.entity.faceclockin.FDAccessLog;

@SpringBootTest
class FDAccessLogDaoTest {

	@Autowired
	FDAccLogRepository dao;
	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void test() throws ParseException {
		String dateStr = "";
		Date date = new Date();
		Gson gson = new Gson();
		DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {

			dateStr = sdf2.format(date);
			System.out.println(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONParser parser = new JSONParser();

		List<FDAccessLog> list = dao.findByCreatedAtStartingWith("2021-01-07");
		List<T100Dto> t100list = new ArrayList<>();
		list.forEach((i) -> {
			try {
				JSONObject json = gson.fromJson(i.getData(), JSONObject.class);
				// JSONObject json = (JSONObject) parser.parse(i.getData());
				MemberInfo data = gson.fromJson(json.get("member_info").toString(), MemberInfo.class);

				// 設定日期格式
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				if (data.getMember_id() != null) {
					t100list.add(new T100Dto(data.getMember_id(), data.getName(), "", "", sdf.parse(i.getCreatedAt()),
							i.getCreatedAt().split(" ")[1], data.getMeal(), data.getGroup_name(),"NEW_FACE"));
				}

			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		t100list.forEach((i) -> {
			System.out.println(i.getEmpId() + " " + i.getEmpName() + " " + i.getDepId() + " " + i.getDepName() + " "
					+ sdf2.format(i.getClockinDate()) + " " + i.getClockinTime() + " " + i.getMealCode());
		});
	}

	@Test
	void testSave() {

//		FDAccessLog obj = new FDAccessLog(); 
//		obj.setData("");
//		obj.setStaffIndex("1674");
//		dao.save(obj);
		// assertEquals(dao.count(), 359);

	}

}
