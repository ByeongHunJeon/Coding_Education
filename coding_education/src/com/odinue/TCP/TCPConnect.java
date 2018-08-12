package com.odinue.TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;


public class TCPConnect {
	
	public static void main(String[] args) {

		URL url=null;
		try {
			url = new URL("http://www.naver.com");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		int port=url.getDefaultPort();
		
		String host=url.getHost();
		
		//System.out.println("host : "+host+" / port : "+ port);
		
		
		
		//서버소켓생성
		Socket socket=null;
		try {
			socket=new Socket(host, port);
			
			InputStream input=socket.getInputStream();
			OutputStream output=socket.getOutputStream();

			byte[] content="<iframe id=\"da_iframe_time\" name=\"da_iframe_time\" src=\"https://nv.veta.naver.com/fxshow?su=SU10079&n…itle=\"광고\" width=\"740\" height=\"120\" marginheight=\"0\" marginwidth=\"0\" scrolling=\"no\" frameborder=\"0\">".getBytes();
	//		byte[] content="<img src=\"https://s.pstatic.net/static/newsstand/up/2017/0424/nsd162528724.png\" height=\"24\" alt=\"전자신문\" class=\"api_logo\">".getBytes();
			byte[] img=new byte[1024*10];
			int bytes=0;
			
			output.write(content);
			
			while ((bytes=input.read(img))>-1) {
				
				System.out.print(new String(img));
				
				
			}
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
