package com.vp.scheduler.servce.tiptop.twfly;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.scheduler.dao.tiptop.twfly.FlyCpfFileRepository;
import com.vp.scheduler.entity.tiptop.twfly.FlyCpfFile;
import com.vp.scheduler.servce.tiptop.FindAllAble;

@Service
public class FlyCpfFileService implements FindAllAble{

	@Autowired
	FlyCpfFileRepository repository;

	public List<FlyCpfFile> findAll() {
		return (List<FlyCpfFile>) repository.findAll();
	}

	public Map<String, String> getEmpIdNameMap() {

		/* Map<String, String> itemMap = */return (repository.findAll()).stream()
				.collect(Collectors.toMap(FlyCpfFile::getCpf01, FlyCpfFile::getCpf02));

//		itemMap.forEach((k, v) -> {
//			System.out.println("key:" + k + ", value:" + v);
//		});

		// return itemMap;
	}
}
