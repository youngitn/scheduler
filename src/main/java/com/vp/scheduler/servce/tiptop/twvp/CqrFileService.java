package com.vp.scheduler.servce.tiptop.twvp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.vp.scheduler.dao.tiptop.twvp.CqrFileRepository;
import com.vp.scheduler.servce.GetAllService;
import com.vp.scheduler.vo.CqrFileVo;
@Primary
@Service("CqrFileService")
public class CqrFileService implements GetAllService{

	@Autowired
	CqrFileRepository dao;

	Date day;

	public CqrFileService() {
		Date date = new Date();
		Instant inst = date.toInstant();
		LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
		Instant dayInst = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

		this.day = Date.from(dayInst);
	}

	/**
	 * 
	 * @Title: getCqrFileByToday @Description: 取得tiptop當日打卡紀錄 @param @return
	 *         List<CqrFileVo> @throws
	 */
	public List<CqrFileVo> getCqrFileByToday() {

		return getCqrFileListByDate(this.day);

	}

	public List<CqrFileVo> getCqrFileByToday(String company) {


		return getCqrFileListByDate(this.day);

	}
	@Override
	public List<CqrFileVo> getCqrFileListByDate(Date day) {

		return dao.findByCqr02OrderByCqr01Desc(day, CqrFileVo.class).stream()
				.collect(Collectors.toCollection(ArrayList::new));

	}

	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return getCqrFileByToday();
	}

}
