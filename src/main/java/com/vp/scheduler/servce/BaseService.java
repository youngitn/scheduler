package com.vp.scheduler.servce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vp.scheduler.entity.Attributes;
import com.vp.scheduler.entity.Errors;
import com.vp.scheduler.entity.SFRes;

import lombok.extern.log4j.Log4j2;

@Log4j2
abstract class BaseService {

	protected static final CloseableHttpClient httpclient = HttpClients.createDefault();
	protected ObjectMapper objectMapper = new ObjectMapper();

	abstract List getListFromT100();

	abstract List passT100ObjToSFObj();

	abstract List resetListType(List list);

	abstract Attributes getAttributes(Object obj);

	public void insertListIntoSF(String token) {
		this.getListFromT100();
		List sfList = resetListType(this.passT100ObjToSFObj());

		
		int allSize = sfList.size();
		int teamNum = 50;
		// 組數
		int teamSize = allSize / teamNum;

		// 餘數
		int lastNum = allSize % teamNum;
		log.debug(allSize);
		log.debug("teamSize=" + teamSize);
		log.debug("lastNum=" + lastNum);
		List tempList = new ArrayList();
		int start = 0;
		
		for (int j = 1; j <= teamSize; j++) {
			int i = 0;
			tempList = sfList.subList(start, (j * teamNum));
			String json = objListToJson(tempList);
			System.out.println("team" + j + "=" + "(" + start + "-" + (j * teamNum) + ")");
			start = start + teamNum;
			String res = doInsert(json, token);
			try {
				SFRes[] aalist = this.objectMapper.readValue(res, SFRes[].class);
				for (SFRes sfRes : aalist) {
					System.out.println(sfRes.isSuccess());
					Errors[] errs = sfRes.getErrors();
					for (Errors err : errs) {
						log.error(getAttributes(tempList.get(i)).getReferenceId()+"---"+ err.getMessage());
					}
					i++;
				}
			} catch (JsonProcessingException e) {
				log.error(e);
			}

		}
		if (lastNum > 0) {
			tempList = sfList.subList(start, allSize);
			String json = objListToJson(tempList);

			System.out.println("team" + (teamSize + 1) + "=" + "(" + start + "-" + allSize + ")");
			String res = doInsert(json, token);

		}

	}

	private String objListToJson(List list) {
		String ujson = "";
		try {
			ujson = "{\"allOrNone\" : false,\"records\": " + this.objectMapper.writeValueAsString(list) + "}";

		} catch (JsonProcessingException e) {

			log.error(e);
		}
		return ujson;
	}

	private String doInsert(String reqjson, String token) {
		String url = "https://cs76.salesforce.com/services/data/v50.0/composite/sobjects";
		HttpPost httppost = new HttpPost(url);
		httppost.addHeader("Content-Type", "application/json;charset=utf-8");
		httppost.addHeader("Authorization", "Bearer " + token);
		StringEntity se = new StringEntity(reqjson, "UTF-8");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		httppost.setEntity(se);
		CloseableHttpResponse response = null;
		String temp = null;

		try {
			response = httpclient.execute(httppost);

			HttpEntity entity1 = response.getEntity();
			temp = EntityUtils.toString(entity1);

		} catch (IOException e) {
			log.error(e);
		}

		return temp;

	}
}
