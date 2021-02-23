package com.vp.scheduler.dao.tiptop.twmd;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.vp.scheduler.entity.tiptop.twmd.MdCpfFile;

@Repository
public interface MdCpfFileRepository extends EntityGraphJpaRepository<MdCpfFile, String> {
	// @EntityGraph(value = "emp.all")
	@EntityGraph(value = "MdCpfFile.depinfo", type = EntityGraphType.FETCH)
	List<MdCpfFile> findAll();
	
	//List<CpfFile> findAll(EntityGraph entityGraph);

}
