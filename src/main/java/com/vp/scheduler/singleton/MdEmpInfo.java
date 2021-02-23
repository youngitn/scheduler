package com.vp.scheduler.singleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.vp.scheduler.entity.tiptop.twmd.MdCpfFile;
import com.vp.scheduler.entity.tiptop.twmd.MdGemFile;
import com.vp.scheduler.entity.tiptop.twvp.CpfFile;
import com.vp.scheduler.entity.tiptop.twvp.GemFile;

import lombok.Data;

@Data
public class MdEmpInfo {
	private static MdEmpInfo instance;

	Map<String, String> empIdAndNameMap = new HashMap<String, String>();

	Map<String, String> depIdAndDepNameMap = new HashMap<String, String>();

	Map<String, String> empIdAndDepIdMap = new HashMap<String, String>();

	public MdEmpInfo(List<MdCpfFile> cpfList, List<MdGemFile> gemList) {

//		cpfList.forEach((o) -> {
//			System.out.println(o.getCpf01() + " " + o.getCpf02());
//		});
		this.empIdAndNameMap = cpfList.stream().collect(Collectors.toMap(MdCpfFile::getCpf01, MdCpfFile::getCpf02));

		this.depIdAndDepNameMap = gemList.stream().collect(Collectors.toMap(MdGemFile::getGem01, MdGemFile::getGem02));

		this.empIdAndDepIdMap = cpfList.stream().collect(Collectors.toMap(MdCpfFile::getCpf01, MdCpfFile::getCpf29));
	}

//	public void show() {
//		this.empIdAndNameMap.forEach((k, v) -> {
//			System.out.println("key:" + k + ", value:" + v);
//		});
//	}

	// 多執行緒時，當物件需要被建立時才使用synchronized保證Singleton一定是單一的 ，增加程式校能
	public static MdEmpInfo getInstance(List<MdCpfFile> cpfService, List<MdGemFile> gemService) {
//		if (instance == null) {
//			System.out.println("instance == null");
//			synchronized (MdEmpInfo.class) {
//				if (instance == null) {
//					instance = new MdEmpInfo(cpfService, gemService);
//				}
//			}
//		}
		return new MdEmpInfo(cpfService, gemService);
	}
}
