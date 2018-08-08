package com.odinue.TCP;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.util.Date;

public class HTTPConnect {
	
	
	public static void main(String[] args) {
		try {
			URL u = new URL("http://www.naver.com");
			
			HttpURLConnection http = (HttpURLConnection) u.openConnection();
			
			http.setRequestMethod("HEAD");
			System.out.println(u+" was last modified at "+new Date(http.getLastModified()));
			
			System.out.println(http.getHeaderFields());
			
			//naver.comÀÇ host¿Í port
			Socket socket=new Socket("125.209.222.141", 80);
			InputStream input=socket.getInputStream();
			OutputStream output=socket.getOutputStream();
			
			


			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
