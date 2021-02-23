package com.vp.scheduler.servce.tiptop.twfly;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.scheduler.dao.tiptop.twfly.FlyCqrFileRepository;
import com.vp.scheduler.servce.GetAllService;
import com.vp.scheduler.vo.CqrFileVo;

@Service("FlyCqrFileService")
public class FlyCqrFileService implements GetAllService{

	@Autowired
	FlyCqrFileRepository dao;

	Date day;

	public FlyCqrFileService() {
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
		Date date = new Date();
		Instant inst = date.toInstant();
		LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
		Instant dayInst = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

		this.day = Date.from(dayInst);
		System.out.println(day);
		return getCqrFileListByDate(this.day);

	}

	public List<CqrFileVo> getCqrFileListByDate(Date day) {

		return dao.findByCqr02OrderByCqr01Desc(day, CqrFileVo.class).stream()
				.collect(Collectors.toCollection(ArrayList::new));

	}

	@Override
	public List getAll() {
		
		return getCqrFileByToday();
	}

}
