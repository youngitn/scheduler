package com.vp.scheduler.servce;

import java.util.Date;
import java.util.List;

public interface GetAllService {

	public List getAll();
	public List getCqrFileListByDate(Date day);
}
