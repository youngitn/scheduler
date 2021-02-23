package com.vp.scheduler.jobs;


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
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * 取得 SALESFORCE OAUTH TOKEN
 * main部分實際開發時需改為一般方法->work
 * @Auther: YangTingCheng
 * @Date: 2020/10/14/上午 08:24
 * @Description:
 */
public class Job1 {
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();
    @Scheduled(cron="0 30 9 * * ?")
    public void work(){
        System.out.println("GOGO");
    }
    @Scheduled(cron="0 31 9 * * ?")
    public void work2(){
        System.out.println("GOGO2");
    }


    public static void main(String[] args) {

        String url = "https://login.salesforce.com/services/oauth2/token" ;
        Map<String,String> map = new HashMap<String,String>();
        map.put("grant_type", "password");
        map.put("client_id", "3MVG9ZL0ppGP5UrC3pzfmpIsi43t.uWyl9pILX2yqqumlmS4KP5irT_dA1f8fwftC5UEONLWwnpTITnvkNFc_");
        map.put("client_secret", "8560455502519422480");
        map.put("username", "martinlee-jfx3@force.com");
        map.put("password", "1qaz2wsx");
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
        HttpEntity entity1 =  response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}

