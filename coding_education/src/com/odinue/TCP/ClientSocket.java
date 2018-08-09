package com.odinue.TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {
	

	//ServerSocket을 먼저 열고 clientSocket을 열어야 함.
	public static void main(String[] args) {
		
		String ip;
		int port;
		
		
		//local ip가져오기
		LocalGetIp getIp=new LocalGetIp();
		ip=getIp.getIp();
		port=getIp.getPort();
		
		//클라이언트소켓
		InputStream input=null;
		OutputStream output=null;
		
		byte[] inputTxt="test request!".getBytes();
		byte[] outputTxt=new byte[10];
		
		try {
			Socket socket=new Socket(ip, port);
			input=socket.getInputStream();
			output=socket.getOutputStream();
			
			
			output.write(inputTxt);
			
			input.read(outputTxt);
			
			System.out.println(new String(outputTxt));
			
			socket.close();
			
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		

	}
	
	
}
