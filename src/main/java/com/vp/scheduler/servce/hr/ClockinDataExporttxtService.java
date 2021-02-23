package com.vp.scheduler.servce.hr;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hierynomus.smbj.session.Session;
import com.hierynomus.smbj.share.File;
import com.vp.scheduler.config.RemoteFileForSMBV2;
import com.vp.scheduler.dao.t100.OrdermealucRepository;
import com.vp.scheduler.dto.t100.T100Dto;
import com.vp.scheduler.servce.GetAllService;
import com.vp.scheduler.servce.faceclockin.FDAccLogService;
import com.vp.scheduler.servce.tiptop.twfly.FlyCpfFileService;
import com.vp.scheduler.servce.tiptop.twfly.FlyCqrFileService;
import com.vp.scheduler.servce.tiptop.twmd.MdCqrFileService;
import com.vp.scheduler.servce.tiptop.twvp.CqrFileService;
import com.vp.scheduler.util.HandleDiskShare;
import com.vp.scheduler.vo.CqrFileVo;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service("ClockinDataExporttxtService")
public class ClockinDataExporttxtService {

	@Autowired
	OrdermealucRepository ordermealucDao;

	@Autowired
	CqrFileService vpcqrService;
	@Autowired
	MdCqrFileService mdcqrService;
	@Autowired
	FlyCqrFileService flycqrService;
	@Autowired
	FDAccLogService faceService;

	/**
	 * 
	 * @Title: getStrByDate @Description: 取得打卡資料後轉為字串回傳 @param @param
	 *         day @param @return @param @throws IOException 設定檔案 @return String
	 *         返回型別 @throws
	 */
	public String getStrByDate(Date day) throws IOException {

		Map<String, List> cqrMap = new HashMap<String, List>();
		cqrMap.put("TWVP", vpcqrService.getCqrFileListByDate(day));
		cqrMap.put("TWMD", mdcqrService.getCqrFileListByDate(day));
		cqrMap.put("TWFLY", flycqrService.getCqrFileListByDate(day));

		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		String str = "";
		for (String i : cqrMap.keySet()) {

			for (CqrFileVo c : (List<CqrFileVo>) cqrMap.get(i)) {
				String meal = "";
				if (c.getCqrno1() != null) {
					meal = c.getCqrno1();
				}
				str += c.getCqr01() + "\t" + sdf.format(c.getCqr02()) + "\t" + c.getCqr03().replace(":", "") + "\t"
						+ meal + "\t" + i + "\r\n";

			}

		}
		for (T100Dto dto : faceService.getT100DtoList(faceService.getCqrFileListByDate(day))) {
			String meal = "";
			if (dto.getMealCode() != null) {
				meal = dto.getMealCode();
			}
			str += dto.getEmpId() + "\t" + sdf.format(dto.getClockinDate()) + "\t"
					+ (dto.getClockinTime().replace(":", "")).substring(0, 4) + "\t" + meal + "\t" + dto.getCpyId()
					+ "\r\n";

		}

		return str;
	}

	public void write(String str, String fileName) throws IOException {
		log.info("開始進行打卡資料匯出動作>>>>>>>>>>>>>>>>>>>");

		HandleDiskShare handle = new HandleDiskShare();
		Session session = new RemoteFileForSMBV2().getSmb2Session();
		File f = handle.writeFile(session, "202011", fileName + ".txt");
		OutputStream outputStream = null;
		try {

			if (f != null) {
				outputStream = f.getOutputStream();

				outputStream.write(str.getBytes());

				outputStream.flush();
				outputStream.close();
				f.flush();
				f.close();
				session.close();
				System.out.println("文件已寫入");

			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
