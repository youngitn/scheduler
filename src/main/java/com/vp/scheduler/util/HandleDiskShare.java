package com.vp.scheduler.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hierynomus.msdtyp.AccessMask;
import com.hierynomus.msfscc.fileinformation.FileIdBothDirectoryInformation;
import com.hierynomus.mssmb2.SMB2CreateDisposition;
import com.hierynomus.mssmb2.SMB2ShareAccess;
import com.hierynomus.mssmb2.SMBApiException;
import com.hierynomus.smbj.session.Session;
import com.hierynomus.smbj.share.DiskShare;
import com.hierynomus.smbj.share.File;
import com.vp.scheduler.config.RemoteFileForSMBV2;

public class HandleDiskShare {

	

	public List<String> listByShareName(Session session, String shareName) {
		ArrayList<String> strings = new ArrayList<>();

		// 这块官方有示例
		DiskShare share = (DiskShare) session.connectShare(shareName);
		List<FileIdBothDirectoryInformation> list = share.list("");
		for (FileIdBothDirectoryInformation information : list) {
			strings.add(information.getFileName());
		}
		return strings;
	}

	public File writeFile(Session session, String shareName, String filePath) throws IOException {
//        filePath = shareName + filePath;
		DiskShare share = (DiskShare) session.connectShare(shareName);
		File f = null;
		int idx = filePath.lastIndexOf("/");
		String folder = "";
		String onlyFileName = filePath.substring(idx + 1, filePath.length());
		if (idx > -1) {
			folder = filePath.substring(0, idx);
		}
		// 文件不存在则创建，存在则打开。
		try {
			System.out.println(folder + java.io.File.separator + onlyFileName);
			f = share.openFile(folder + onlyFileName,
					new HashSet(Arrays.asList(new AccessMask[] { AccessMask.GENERIC_ALL })), (Set) null,
					SMB2ShareAccess.ALL, SMB2CreateDisposition.FILE_CREATE, (Set) null);
		} catch (SMBApiException e) {
			// 此处取了个巧，捕获了其文件已存在时抛出的异常
			if (e.getMessage().contains("STATUS_OBJECT_NAME_COLLISION")) {
				System.out.println("已存在文件進行寫入操作");
				System.out.println(folder + java.io.File.separator + onlyFileName);
				f = share.openFile(folder + onlyFileName,
						new HashSet(Arrays.asList(new AccessMask[] { AccessMask.GENERIC_ALL })), (Set) null,
						SMB2ShareAccess.ALL, SMB2CreateDisposition.FILE_OVERWRITE, (Set) null);
			}
		}
//		OutputStream outputStream = null;
//		String str = "Hello World";
//		if (f != null) {
//			outputStream = f.getOutputStream();
//			outputStream.write(str.getBytes());
//			outputStream.flush();
//			outputStream.close();
//			System.out.println("文件已寫入");
//		}
//		return true;
		
		return f;
	}
}
