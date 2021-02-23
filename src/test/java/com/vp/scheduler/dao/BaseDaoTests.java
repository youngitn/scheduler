package com.vp.scheduler.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vp.scheduler.entity.account.T100Account;

@SpringBootTest
public class BaseDaoTests {
	
	@Test
	void getDataFromT100Test() throws JsonMappingException, JsonProcessingException {
		BaseDao bd = new BaseDao("account");
		String list = (String) bd.getDataFromT100();
		//System.out.print(list);
		ObjectMapper objectMapper = new ObjectMapper();
		List<T100Account> l = objectMapper.readValue(list, new TypeReference<List<T100Account>>() {
			});
		
		System.out.println(l.get(0).getAccount_name());
		
	}

}
