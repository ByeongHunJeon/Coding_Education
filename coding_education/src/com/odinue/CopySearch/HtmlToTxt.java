package com.odinue.CopySearch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;

import com.odinue.CopySearch.FileTraverse;


public class HtmlToTxt extends FileTraverse.FileHandler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FileTraverse ft = new FileTraverse(new HtmlToTxt() ) ;
		int cnt=ft.t(new File(args[0]));
		System.out.println(cnt+"개의 파일을 처리하였습니다.");
		
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
				
				Stack<Character> stack=new Stack<>();
				StringBuilder sb=new StringBuilder();
				
				int isc=-1;
				boolean flag=true;
				//<태그가 들어오면 출력 flag를 off해주고 >태그가 들어오면 출력 flag를 다시 on한다.
				while ((isc=inputFile.read())>-1) {
					//System.out.println((char)isc);
					
					
					
					//<style 일때 </style>이 될때까지 flag값을 어떻게 false로 만들어주는가?
					//이 처리를 할 경우 퍼포먼스가 현저히 줄어든다.
/*					if (sb.indexOf("<style")>-1) {
						
						flag=false;
						
					}else if (sb.indexOf("</style")>-1) {
						
						flag=true;
						
					}
					
					if (sb.indexOf("<script")>-1) {
						
						flag=false;
						
					}else if (sb.indexOf("</script")>-1) {
						
						flag=true;
						
					}*/
					
					
					
					//<태그가 시작되면 출력 flag를 off하고 스택에 넣어서 예외처리를 하거나 <>를 삭제한다.
					if ((char)isc=='<') {
						
						flag=false;
						
					}
					if ((char)isc=='>') {
					
						flag=true;

					}
					
					
					//flag 상태에 따라서 출력 여부가 결정된다.
					if (flag) {
						outFile.write(isc);
						
					}else {
						sb.append((char)isc);
						
					}
					
					//escape문자를 원문자로 복원해주는 처리

					
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