package com.odinue.TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerSocket {
	
	public ServerSocket() {
	}
	
	public static void main(String[] args) {
		
		LocalGetIp getAddr=new LocalGetIp();
		int port=getAddr.getPort();
		
		//서버소켓
		try {

			java.net.ServerSocket server=new java.net.ServerSocket(port);
			Socket socket=server.accept();
			
			InputStream input=socket.getInputStream();
			OutputStream output=socket.getOutputStream();
			
			byte[] bytes =new byte[1024*10];
			
			input.read(bytes);
			
			System.out.println(new String(bytes));
		
			byte[] outTxt="response Test".getBytes();
			
			output.write(outTxt);
			
			System.out.println(new String(outTxt));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
