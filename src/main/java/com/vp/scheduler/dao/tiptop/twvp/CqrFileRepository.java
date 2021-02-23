package com.vp.scheduler.dao.tiptop.twvp;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vp.scheduler.entity.tiptop.twvp.CqrFile;
import com.vp.scheduler.vo.CqrFileVo;

/**
 * 
 * @ClassName: CqrFileDtoRepository
 * @Description: CqrFileDto=>entity,CqrFileVo=> vo in Query should know : 1.that
 *               CqrFileDto=class name not table name. 2.The column aliases must
 *               match the names in your vo interface. 3.if using @Query
 *               無法直接轉成物件
 * @author ytc
 * @date 2020年12月30日 上午10:02:56
 *
 */
@Repository
public interface CqrFileRepository extends JpaRepository<CqrFile, String> {

	@Query("SELECT " + "a.cqr01 as cqr01," + "a.cqr02 as cqr02," + "a.cqr03 as cqr03, " + "a.cqrno1 as cqrno1, "
			+ "a.cqrno2 as cqrno2 " + "FROM CqrFile a WHERE a.cqr02 = TO_DATE( :cqr02 ,'YYYY-MM-DD')")
	List<CqrFileVo> findByDate(String cqr02);
	
	<T> Collection<T> findByCqr02OrderByCqr01Desc(Date cqr02, Class<T> type);

	List<CqrFile> findByCqr02Between(Date startCqr02, Date endCqr02);
	// <T> Collection<T> findByCqr02Between(Date startCqr02,Date endCqr02, Class<T>
	// type);
}
