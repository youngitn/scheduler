package com.vp.scheduler.servce.t100;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.scheduler.dao.faceclockin.FDAccLogRepository;
import com.vp.scheduler.dto.t100.T100Dto;
import com.vp.scheduler.servce.tiptop.twmd.MdCpfFileService;
import com.vp.scheduler.servce.tiptop.twmd.MdGemFileService;
import com.vp.scheduler.singleton.MdEmpInfo;
import com.vp.scheduler.vo.CqrFileVo;

/**
 * 
 * @ClassName: ClockinDataIntegrationService
 * @Description: 打卡資料整合
 * @author ytc
 * @date 2020年12月30日 下午1:21:07
 *
 */
@Service
public class MdClockinDataIntegrationService {

	@Autowired
	FDAccLogRepository fdaccLogservice;

	@Autowired
	MdCpfFileService cpfFileService;
	@Autowired
	MdGemFileService gemFileService;

	@Autowired
	MdClockinDataIntegrationService service;

	List<CqrFileVo> inputList;

	/**
	 * 
	 * @Title: getMappedList @Description: 取得整合員工姓名-員工編號 &
	 * 部門名稱-部門編號的list<T100Dto> @param @return 設定檔案 @return List<T100Dto>
	 * 返回型別 @throws
	 */
	public List<T100Dto> getMappedList() {
		MdEmpInfo empInfo = MdEmpInfo.getInstance(cpfFileService.findAll(), gemFileService.findAll());
		Map<String, String> empIdAndDepIdMap = empInfo.getEmpIdAndDepIdMap();
		Map<String, String> empIdAndNameMap = empInfo.getEmpIdAndNameMap();
		Map<String, String> depIdAndDepNameMap = empInfo.getDepIdAndDepNameMap();
		List<T100Dto> t100DtoList = new ArrayList<T100Dto>();
		inputList.forEach((i) -> {
			// DepId
			String depId = empIdAndDepIdMap.get(i.getCqr01());
//			String depId ="";
			// EmpName
			String empName = empIdAndNameMap.get(i.getCqr01());
//			String empName ="";
			// DepName
			String depName = depIdAndDepNameMap.get(depId);
//			String depName = "";
			t100DtoList.add(new T100Dto(i.getCqr01(), empName, depId, depName, i.getCqr02(), i.getCqr03(),
					i.getCqrno1(), "TWMD","TIPTOP"));

		});

		return t100DtoList;
	}

	public void setList(List<CqrFileVo> list) {

		this.inputList = list;
	}

}
