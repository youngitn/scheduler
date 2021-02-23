package com.vp.scheduler.servce.tiptop.twmd;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.scheduler.dao.tiptop.twmd.MdCpfFileRepository;
import com.vp.scheduler.entity.tiptop.twmd.MdCpfFile;
import com.vp.scheduler.servce.tiptop.FindAllAble;

@Service
public class MdCpfFileService implements FindAllAble{

	@Autowired
	MdCpfFileRepository repository;

	public List<MdCpfFile> findAll() {
		return (List<MdCpfFile>) repository.findAll();
	}

	public Map<String, String> getEmpIdNameMap() {

		/* Map<String, String> itemMap = */
		return (repository.findAll()).stream().collect(Collectors.toMap(MdCpfFile::getCpf01, MdCpfFile::getCpf02));

//		itemMap.forEach((k, v) -> {
//			System.out.println("key:" + k + ", value:" + v);
//		});

		// return itemMap;
	}
}
