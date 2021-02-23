package com.vp.scheduler.servce.tiptop.twmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vp.scheduler.dao.tiptop.twmd.MdGemFileRepository;
import com.vp.scheduler.entity.tiptop.twmd.MdGemFile;
import com.vp.scheduler.servce.tiptop.FindAllAble;

@Service
public class MdGemFileService implements FindAllAble{

	@Autowired
	MdGemFileRepository rep;

	public List<MdGemFile> findAll() {
		return rep.findAll();
	}
}
