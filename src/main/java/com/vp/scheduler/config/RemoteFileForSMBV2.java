package com.vp.scheduler.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hierynomus.smbj.SMBClient;
import com.hierynomus.smbj.SmbConfig;
import com.hierynomus.smbj.auth.AuthenticationContext;
import com.hierynomus.smbj.connection.Connection;
import com.hierynomus.smbj.session.Session;


public class RemoteFileForSMBV2 {
	/**
	 * ip
	 **/
	private final String hostName = "192.168.0.3";
	/**
	 * 帳號
	 **/
	private final String username = "administrator";
	/**
	 * 密碼
	 **/
	private final String password = "VP@56650862";

	
	public Session getSmb2Session() {

		Session s = null;
		try {
			SMBClient client = new SMBClient(SmbConfig.createDefaultConfig());
			Connection c = client.connect(hostName);
			System.out.println("是否連接：" + c.isConnected());
			s = c.authenticate(new AuthenticationContext(username, password.toCharArray(), ""));
			return s;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
}
