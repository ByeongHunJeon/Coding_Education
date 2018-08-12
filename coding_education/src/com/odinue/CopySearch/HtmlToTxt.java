package com.odinue.CopySearch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.odinue.CopySearch.FileTraverse;


public class HtmlToTxt extends FileTraverse.FileHandler {

	public static void main(String[] args) {
		
		FileTraverse ft = new FileTraverse(new HtmlToTxt() ) ;
		int cnt=ft.t(new File(args[0]));
		System.out.println(cnt+"개의 파일을 처리하였습니다.");
		
	}
	

	@Override
	public int handle(File f) {
		
		
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
				
				StringBuilder sb=new StringBuilder();
				
				int bytes=-1;
				boolean flag=false;
				
				//<태그가 들어오면 출력 flag를 off해주고 >태그가 들어오면 출력 flag를 다시 on한다. 
				while ((bytes=inputFile.read())>-1) {
					
					//<태그가 시작되면 출력 flag를 off하고 스택에 넣어서 예외처리를 하거나 <>를 삭제한다.
					if ((char)bytes=='<') {
						
						flag=false;
						
					}
					
					//escape문자를 원문자로 복원해주는 처리 시작부분
					if ((char)bytes=='&') {
						
					//	System.out.println("escape문자  처리시작");
						
						flag=false;
						
					}
					
					//escape문자를 검사해서 변환해주는 분기문
					if (sb.indexOf("&lt")>-1) {
						outFile.write("<".getBytes());
						
					}
					
					if (sb.indexOf("&gt")>-1) {
						outFile.write(">".getBytes());
						
					}
					if (sb.indexOf("&nbsp")>-1) {
						outFile.write("\t".getBytes());
						
					}
					if (sb.indexOf("&quot")>-1) {
						outFile.write("\"".getBytes());

					}
					if (sb.indexOf("&amp")>-1) {
						outFile.write("&".getBytes());
						
					}
					if (sb.indexOf("&copy")>-1) {
						outFile.write("copy".getBytes());
						
					}
					if (sb.indexOf("&trade")>-1) {
					//	outFile.write("™".getBytes());
						
					}
					
					//flag 상태에 따라서 출력 여부가 결정된다.
					if (flag) {
						outFile.write(bytes);
					}else {
						sb.append((char)bytes);
					}
					
					
					//script나 style 안에 >가 포함되어 있으면 flag 작동 : 논리적으로  flag값에 대한 정의가 필요
					if ((char)bytes=='>') {
						
						flag=true;

					}
					
					
					
					//<script 일때 </script>이 될때까지 flag값을 false로 변경해주고 sb에 담아두었던것을 ""으로 변경해서 초기화
					if (sb.indexOf("<script type=\"text/javascript\">")>-1) {
						
						flag=false;
						sb.replace(0, sb.length(), "");
						
					}else if (sb.indexOf("</script>")>-1) {
						flag=true;
						sb.replace(0, sb.length(), "");
					}
					
					
					//script태그 처리와 동일하게 처리
					if (sb.indexOf("<style type=\"text/css\">")>-1) {
						
						
						flag=false;
						sb.replace(0, sb.length(), "");
						
					}else if (sb.indexOf("</style>")>-1) {
						flag=true;
						sb.replace(0, sb.length(), "");
						
					}
					
					


					
					
					//escape문자처리가 닫히면 sb를 초기화해주고 출력 flag를 on
					//if ((char)bytes==';') {
					if (flag) {
					
						if (sb.indexOf("lt;")>-1) {
								flag=true;
								sb.replace(0, sb.length(), "");
							//	System.out.println("escape 처리 완료");
						}
						if (sb.indexOf("gt;")>-1) {
							flag=true;
							sb.replace(0, sb.length(), "");
							
						}
						if (sb.indexOf("nbsp;")>-1) {
							flag=true;
							sb.replace(0, sb.length(), "");
						}
						if (sb.indexOf("quot;")>-1) {
							flag=true;
							sb.replace(0, sb.length(), "");
						}
						if (sb.indexOf("amp;")>-1) {
							flag=true;
							sb.replace(0, sb.length(), "");
						}
						if (sb.indexOf("copy;")>-1) {
							flag=true;
							sb.replace(0, sb.length(), "");
						}
						if (sb.indexOf("trade;")>-1) {
							flag=true;
							sb.replace(0, sb.length(), "");
						}
						
					}

				//	System.out.println("sb 내용확인:"+sb);
						
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