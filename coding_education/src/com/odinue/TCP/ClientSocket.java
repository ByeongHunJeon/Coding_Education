package com.odinue.TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {
	

	//ServerSocket�� ���� ���� clientSocket�� ����� ��.
	public static void main(String[] args) {
		
		String ip;
		int port;
		
		
		//local ip��������
		LocalGetIp getIp=new LocalGetIp();
		ip=getIp.getIp();
		port=getIp.getPort();
		
		//Ŭ���̾�Ʈ����
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
