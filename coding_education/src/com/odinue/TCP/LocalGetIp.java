package com.odinue.TCP;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalGetIp {
	
	private InetAddress myIp;

	private String ip;
	
	private int port;

	public String getIp() {
		
		
		try {
			myIp=InetAddress.getLocalHost();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		System.out.println("ip : "+myIp);
		
		return ip;
	}
	
	public int getPort() {
		
		port=89;
		
		return port;
	}

}
