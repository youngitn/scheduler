package com.vp.scheduler.dao.t100;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vp.scheduler.entity.t100.Ordermealuc_t;

@Repository
public interface OrdermealucRepository extends CrudRepository<Ordermealuc_t, String> {

}
