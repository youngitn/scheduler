package com.vp.scheduler.dto.t100;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * 
* @ClassName: T100Dto 
* @Description: String empId, String empName, String clockinDate, String clockinTime, String mealCode
* @author ytc
* @date 2020年12月30日 下午4:05:31 
*
 */
@AllArgsConstructor
@Data
public class T100Dto {
	
	private String empId;
	private String empName;
	private String depId;
	private String depName;
	private Date clockinDate;
	private String clockinTime;
	private String mealCode;
	private String cpyId;
	private String source;
	
	
	
}
