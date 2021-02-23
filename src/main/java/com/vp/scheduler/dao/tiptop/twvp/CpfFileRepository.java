package com.vp.scheduler.dao.tiptop.twvp;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.vp.scheduler.entity.tiptop.twvp.CpfFile;

@Repository
public interface CpfFileRepository extends EntityGraphJpaRepository<CpfFile, String> {
	// @EntityGraph(value = "emp.all")
	@EntityGraph(value = "CpfFile.depinfo", type = EntityGraphType.FETCH)
	List<CpfFile> findAll();
	

}
