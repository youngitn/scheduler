package com.vp.scheduler.dao;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vp.scheduler.entity.SFRes;
import com.vp.scheduler.entity.T100Rep;
import com.vp.scheduler.util.JSONFileReader;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * 
 * @ClassName: Obj
 * @Description: 父類別
 * @author ytc
 * @date 2020年11月17日 上午11:22:25
 *
 */

@Data
@Log4j2
public class BaseDao {

	private String url = "http://192.168.0.5/wstopprd/ws/r/awsp920";
	private String reqJSONString;
	private String reqName;
	private String patch = "D:\\T100reqFile\\";
	private HttpPost httppost = new HttpPost(url);
	// 宣告rep
	private CloseableHttpResponse response = null;
	private String temp = null;

	private ObjectMapper objectMapper = new ObjectMapper();
	private static final CloseableHttpClient httpclient = HttpClients.createDefault();
	private T100Rep rep;
	public BaseDao(String reqName) {
		// req名稱
		this.reqName = reqName;
		// 從file抓json req,並賦值給reqJSONString
		this.setReq();
		// 設定Header 指定為req為JSON
		this.httppost.addHeader("Content-Type", "application/json");
		StringEntity se = new StringEntity(reqJSONString, "UTF-8");
		httppost.setEntity(se);
	}

	public String getDataFromT100() {
		log.info("getDataFromT100");
		String urlist = null;
		try {
			response = httpclient.execute(httppost);

			this.rep = new T100Rep(response, reqName);
			urlist = rep.getMaster().toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return urlist;
	}

	SFRes setDataToSalesforceObject() {
		return null;
	}

	private void setReq() {

		JSONFileReader jsonFileReader = new JSONFileReader(patch + "web." + reqName + ".get.json");
		JSONObject jo = jsonFileReader.getJSONObject();
		this.reqJSONString = jo.toString();

	}

}
