package com.vp.scheduler.util;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import com.hierynomus.msdtyp.AccessMask;
import com.hierynomus.msfscc.fileinformation.FileIdBothDirectoryInformation;
import com.hierynomus.mssmb2.SMB2CreateDisposition;
import com.hierynomus.mssmb2.SMB2ShareAccess;
import com.hierynomus.smbj.SMBClient;
import com.hierynomus.smbj.SmbConfig;
import com.hierynomus.smbj.auth.AuthenticationContext;
import com.hierynomus.smbj.connection.Connection;
import com.hierynomus.smbj.session.Session;
import com.hierynomus.smbj.share.DiskShare;
import com.hierynomus.smbj.share.File;

public class SMB2Connect {
	private static final String SHARE_DOMAIN = "";
	private static final String SHARE_USER = "administrator";
	private static final String SHARE_PASSWORD = "VP@56650862";
	private static final String SHARE_SRC_DIR = "202011";
	//private static final String SHARE_DST_DIR = "20201201.txt";

	public static void main(String[] args) {
		// 設定超時時間(可選)
		SmbConfig config = SmbConfig.builder().withTimeout(120, TimeUnit.SECONDS)
				.withTimeout(120, TimeUnit.SECONDS) // 超時設定讀，寫和Transact超時（預設為60秒）
	            .withSoTimeout(180, TimeUnit.SECONDS) // Socket超時（預設為0秒）
	            .build();
		
		// 如果不設定超時時間	SMBClient client = new SMBClient();
		SMBClient client = new SMBClient(config);

		try {
			Connection connection = client.connect("192.168.0.3");	// 如:123.123.123.123
			AuthenticationContext ac = new AuthenticationContext(SHARE_USER, SHARE_PASSWORD.toCharArray(), SHARE_DOMAIN);
			Session session = connection.authenticate(ac);

			// 連線共享資料夾
			DiskShare share = (DiskShare) session.connectShare(SHARE_SRC_DIR);
			
			String folder = SHARE_SRC_DIR ;
			String dstRoot = "D:/smd/";	// 如: D:/smd2/
			System.out.println(share.list("", "*.txt").size());
			for (FileIdBothDirectoryInformation f : share.list("", "*.txt")) {
				
				String filePath = folder + f.getFileName();
				String dstPath = dstRoot + f.getFileName();
				System.out.println(dstPath);
				FileOutputStream fos = new FileOutputStream(dstPath);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				System.out.println(bos.toString());
				if (share.fileExists(filePath)) {
					System.out.println("正在下載檔案:" + f.getFileName());
					
					File smbFileRead = share.openFile(filePath, EnumSet.of(AccessMask.GENERIC_READ), null, SMB2ShareAccess.ALL, SMB2CreateDisposition.FILE_OPEN, null);
					InputStream in = smbFileRead.getInputStream();
					byte[] buffer = new byte[4096];
					int len = 0;
					while ((len = in.read(buffer, 0, buffer.length)) != -1) {
						bos.write(buffer, 0, len);
					}
					
					bos.flush();
					bos.close();
					
					System.out.println("檔案下載成功");
					System.out.println("==========================");
				} else {
					System.out.println("檔案不存在");
				}
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}
}