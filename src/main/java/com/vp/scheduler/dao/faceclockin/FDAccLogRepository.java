package com.vp.scheduler.dao.faceclockin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vp.scheduler.entity.faceclockin.FDAccessLog;

@Repository
public interface FDAccLogRepository extends JpaRepository<FDAccessLog, String> {

	List<FDAccessLog> findByCreatedAt(String createdAt);

	List<FDAccessLog> findByCreatedAtStartingWith(String createdAt);

}
