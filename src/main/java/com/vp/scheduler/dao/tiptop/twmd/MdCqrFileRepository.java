package com.vp.scheduler.dao.tiptop.twmd;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vp.scheduler.entity.tiptop.twmd.MdCqrFile;

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
public interface MdCqrFileRepository extends JpaRepository<MdCqrFile, String> {

	<T> Collection<T> findByCqr02OrderByCqr01Desc(Date cqr02, Class<T> type);

}
