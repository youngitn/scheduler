package com.vp.scheduler.jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vp.scheduler.entity.SalesForceOAth;
/**
 * 
* @ClassName: JsonStringToSalesForceOAthObj 
* @Description: JSON授權 字串轉 SalesForceOAth物件
* @author ytc
* @date 2020年11月2日 上午9:15:04 
*
 */
public class JsonStringToSalesForceOAthObj {

	public SalesForceOAth CreateObj(String some) {
		
		//String some = "{\"access_token\":\"00D9D0000000WA7!AR0AQDeMRssrqiEhm6n_W4TNF_.wNcSiCirhP9MzW0iSSaLOUDUIJOqamHl4b36UBoXoahoxPFdykxriV9HRtXV1rJ85WlLq\",\"instance_url\":\"https://cs76.salesforce.com\",\"id\":\"https://test.salesforce.com/id/00D9D0000000WA7UAM/0052x000001wjyAAAQ\",\"token_type\":\"Bearer\",\"issued_at\":\"1603957980351\",\"signature\":\"WCfUWNnSvIQABb6IqZIqFXAg5dDRn2V0H9wr3GKJVeQ=\"}";
		SalesForceOAth sfOath = null;
		try {
			sfOath = new ObjectMapper().readValue(some, SalesForceOAth.class);
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		} 
		System.out.print(sfOath.getAccess_token());
		return sfOath;
	}

}
