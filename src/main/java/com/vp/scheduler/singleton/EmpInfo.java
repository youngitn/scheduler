package com.vp.scheduler.singleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.vp.scheduler.entity.tiptop.twvp.CpfFile;
import com.vp.scheduler.entity.tiptop.twvp.GemFile;

import lombok.Data;

@Data
public class EmpInfo {
	private static EmpInfo instance;

	Map<String, String> empIdAndNameMap = new HashMap<String, String>();

	Map<String, String> depIdAndDepNameMap = new HashMap<String, String>();

	Map<String, String> empIdAndDepIdMap = new HashMap<String, String>();

	public EmpInfo(List<CpfFile> cpfList, List<GemFile> gemList) {
		
//		cpfList.forEach((o)->{
//			System.out.println(o.getCpf01()+" "+o.getCpf02() );
//		});
		this.empIdAndNameMap = cpfList.stream().collect(Collectors.toMap(CpfFile::getCpf01, CpfFile::getCpf02));

		this.depIdAndDepNameMap = gemList.stream().collect(Collectors.toMap(GemFile::getGem01, GemFile::getGem02));

		this.empIdAndDepIdMap = cpfList.stream().collect(Collectors.toMap(CpfFile::getCpf01, CpfFile::getCpf29));
	}

//	public void show() {
//		this.empIdAndNameMap.forEach((k, v) -> {
//			System.out.println("key:" + k + ", value:" + v);
//		});
//	}

	// 多執行緒時，當物件需要被建立時才使用synchronized保證Singleton一定是單一的 ，增加程式校能
	public static EmpInfo getInstance(List<CpfFile> cpfService, List<GemFile> gemService) {
//		if (instance == null) {
//			System.out.println("instance == null");
//			synchronized (EmpInfo.class) {
//				if (instance == null) {
//					instance = new EmpInfo(cpfService, gemService);
//				}
//			}
//		}
		return  new EmpInfo(cpfService, gemService);
	}
}
