package com.vp.scheduler.servce.tiptop.twvp;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.scheduler.dao.tiptop.twvp.CpfFileRepository;
import com.vp.scheduler.entity.tiptop.twvp.CpfFile;
import com.vp.scheduler.servce.tiptop.FindAllAble;

@Service
public class CpfFileService implements FindAllAble{

	@Autowired
	CpfFileRepository repository;

	public List<CpfFile> findAll() {
		return (List<CpfFile>) repository.findAll();
	}

	public Map<String, String> getEmpIdNameMap() {

		/* Map<String, String> itemMap = */return (repository.findAll()).stream()
				.collect(Collectors.toMap(CpfFile::getCpf01, CpfFile::getCpf02));

//		itemMap.forEach((k, v) -> {
//			System.out.println("key:" + k + ", value:" + v);
//		});

		// return itemMap;
	}
}
