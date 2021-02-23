package com.vp.scheduler.servce.tiptop.twfly;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.scheduler.dao.tiptop.twfly.FlyGemFileRepository;
import com.vp.scheduler.entity.tiptop.twfly.FlyGemFile;
import com.vp.scheduler.servce.tiptop.FindAllAble;

@Service
public class FlyGemFileService implements FindAllAble{

	@Autowired
	FlyGemFileRepository rep;

	public List<FlyGemFile> findAll() {
		return rep.findAll();
	}
}
