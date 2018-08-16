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
					
					
					
					
					//<script 일때 </script>이 될때까지 flag값을 false로 변경해주고 sb에 담아두었던것을 ""으로 변경해서 초기화
					if (sb.indexOf("<script")>-1) {
						
						flag=false;
						
					}
					//script안에 > 태그가 있을 경우 계속 flag를 off시켜줌
					if (sb.indexOf("<script")>-1 && sb.indexOf(">")>-1 ) {
						flag=false;
						
					}else if (sb.indexOf("</script>")>-1) {
						flag=true;
						sb.replace(0, sb.length(), "");
					}

					
					
					//script태그 처리와 동일하게 처리
					if (sb.indexOf("<style")>-1) {
						
						flag=false;
						
					}
					if (sb.indexOf("<style")>-1 && sb.indexOf(">")>-1 ) {
						flag=false;
						
					}else if (sb.indexOf("</style>")>-1) {
						flag=true;
						sb.replace(0, sb.length(), "");
						
					}
					
					
					//<script>,<style>태그가 아니고, 주석에 속하지 않았을 경우  닫히는 태그( > ) 검사
					if ( (!(sb.indexOf("<style")>-1) || !(sb.indexOf("<script")>-1) ||!(sb.indexOf("<!--")>-1) ) && (char)bytes=='>') {
						
						flag=true;

					}
					
					//주석안에서 태그가 닫히는 것을 방지해서 출력을 꺼준다.
					if ((sb.indexOf("<!--")>-1) && (char)bytes=='>') {

						flag=false;
					}
					
					
					//주석으로 시작하여 -->로 닫히는 경우 출력을 해준다.
					if (sb.indexOf("-->")>-1) {

						flag=true;
						sb.replace(0, sb.length(), "");
					}
					
					
					
					//escape문자처리가 닫히면 sb를 초기화해주고 출력 flag를 on
					if (!flag) {
					
						//escape 문자중에 아래에 해당하지 않는 문자가 들어왔을때의 처리는?
						if (sb.indexOf("lt;")>-1) {
							flag=true;
							sb.replace(0, sb.length(), "");
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