package com.odinue.CopySearch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;

import com.odinue.CopySearch.FileTraverse;


public class Html2Text extends FileTraverse.FileHandler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FileTraverse ft = new FileTraverse(new Html2Text() ) ;
		int cnt=ft.t(new File(args[0]));
		System.out.println(cnt+"���� ������ ó���Ͽ����ϴ�.");
		
	}
	

	@Override
	public int handle(File f) {
		// TODO Auto-generated method stub
		
		
		if ( f.getName().toUpperCase().endsWith(".HTML")) {
			//System.out.println( f.getPath());
			
			String HtmlPath=f.getPath();
			String txtPath=HtmlPath.substring(0, HtmlPath.lastIndexOf("."))+".txt";
			
			System.out.println(txtPath);

			FileInputStream inputFile=null;
			FileOutputStream outFile=null;
			try {
				inputFile=new FileInputStream(f);
				outFile=new FileOutputStream(new File(txtPath));
				
			//	Stack<Character> stack=new Stack<>();
				StringBuilder sb=new StringBuilder();
				
				int isc=-1;
				boolean flag=false;
				//<�±װ� ������ ��� flag�� off���ְ� >�±װ� ������ ��� flag�� �ٽ� on�Ѵ�.
				while ((isc=inputFile.read())>-1) {
					//System.out.println((char)isc);
					
					//<�±װ� ���۵Ǹ� ��� flag�� off�ϰ� ���ÿ� �־ ����ó���� �ϰų� <>�� �����Ѵ�.
					if ((char)isc=='<') {
						
						flag=false;
						
					}
					if ((char)isc=='>') {
					
						flag=true;

					}
					
					//<style �϶� </style>�� �ɶ����� flag���� ��� false�� ������ִ°�?
					if (sb.indexOf("<style")>-1) {
						
					}
					
					//flag ���¿� ���� ��� ���ΰ� �����ȴ�.
					if (flag) {
						outFile.write(isc);
						
					}else {
					//	stack.add((char)isc);
						sb.append((char)isc);
					}
					
					//escape���ڸ� �����ڷ� �������ִ� ó��
					if (sb.indexOf("&gt;")>-1) {
						outFile.write("-----------".getBytes());
						
					}
					
				//	System.out.println(stack.toString().replaceAll(",", ""));\
				//	System.out.println(sb);
						
				}
				
				
				
				
				outFile.close();
				outFile=null;
				inputFile.close();
				inputFile=null;
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if (outFile!=null) {
						outFile.close();
					}
					if (inputFile!=null) {
						inputFile.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			
			
			return 1;
		}
		return 0;
	}

}