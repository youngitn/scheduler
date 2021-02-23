package com.vp.scheduler.servce.tiptop.twvp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.scheduler.dao.tiptop.twvp.GemFileRepository;
import com.vp.scheduler.entity.tiptop.twvp.GemFile;
import com.vp.scheduler.servce.tiptop.FindAllAble;

@Service
public class GemFileService implements FindAllAble{

	@Autowired
	GemFileRepository rep;

	public List<GemFile> findAll() {
		return (List<GemFile>) rep.findAll();
	}
}
