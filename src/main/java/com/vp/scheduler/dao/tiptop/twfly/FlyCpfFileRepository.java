package com.vp.scheduler.dao.tiptop.twfly;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.vp.scheduler.entity.tiptop.twfly.FlyCpfFile;

@Repository
public interface FlyCpfFileRepository extends EntityGraphJpaRepository<FlyCpfFile, String> {
	// @EntityGraph(value = "emp.all")
	@EntityGraph(value = "FlyCpfFile.depinfo", type = EntityGraphType.FETCH)
	List<FlyCpfFile> findAll();
	
	//List<CpfFile> findAll(EntityGraph entityGraph);

}
