package com.odinue.TCP;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class HTTPConnect {
	
	
	public static void main(String[] args) {
		try {
			URL u = new URL("http://www.naver.com");
			
			HttpURLConnection http = (HttpURLConnection) u.openConnection();
			
			http.setRequestMethod("HEAD");
			System.out.println(u+" was last modified at "+new Date(http.getLastModified()));


			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
