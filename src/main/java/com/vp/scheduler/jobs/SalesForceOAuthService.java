package com.vp.scheduler.jobs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.vp.scheduler.entity.SalesForceOAth;

public class SalesForceOAuthService {

	private static final CloseableHttpClient httpclient = HttpClients.createDefault();

	
	public String getToken() {
		String url = "https://cs76.salesforce.com/services/oauth2/token";
		Map<String, String> map = new HashMap<String, String>();
		map.put("grant_type", "password");
		map.put("client_id", "3MVG9N6eDmZRVJOl9iJC6WdUp.WPB9PCt8jOEFDnNfwgR7bYswTnhkjKQTR3ebVLFpbxVrP.XVSmZEgKFCoIj");
		map.put("client_secret", "669D2176175407290A8FF7DC727FCEA276BFCD3B59A8EF46AC05ACD87F7EA8AA");
		map.put("username", "walk-kk@vp.com.walktest");
		map.put("password", "walkvp99");
		List<BasicNameValuePair> formparams = new ArrayList<>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(entity);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpEntity entity1 = response.getEntity();
		String result = null;
		try {
			result = EntityUtils.toString(entity1);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		// System.out.println(result);
		JsonStringToSalesForceOAthObj jsonToObj = new JsonStringToSalesForceOAthObj();
		SalesForceOAth sfo = jsonToObj.CreateObj(result);
		return sfo.getAccess_token();
	}
}
