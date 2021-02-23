package com.vp.scheduler.util;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JSONFileReaderTests {
	
	@Test
	void readJSONFileTest() {
		JSONFileReader rj = new JSONFileReader("D:\\T100reqFile\\web.quote.get.json");
		JSONObject jso = rj.getJSONObject();
		JSONObject a = (JSONObject)jso.get("service");
		//System.out.print(a);
		System.out.print(rj.getJSONString());
	}
	

}
