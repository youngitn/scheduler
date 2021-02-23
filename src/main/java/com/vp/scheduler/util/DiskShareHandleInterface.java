package com.vp.scheduler.util;

import java.io.IOException;
import java.util.List;

public interface DiskShareHandleInterface {
	List<String> listByShareName(String shareName);

	boolean writeFile(String shareName, String filePath) throws IOException;
}
