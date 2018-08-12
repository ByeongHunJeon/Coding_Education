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
		
		FileTraverse ft = new FileTraverse(new Html2Text() ) ;
		int cnt=ft.t(new File(args[0]));
		System.out.println(cnt+"���� ������ ó���Ͽ����ϴ�.");
		
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
				
				int isc=-1;
				boolean flag=false;
				
				//<�±װ� ������ ��� flag�� off���ְ� >�±װ� ������ ��� flag�� �ٽ� on�Ѵ�.
				while ((isc=inputFile.read())>-1) {
					
					//<�±װ� ���۵Ǹ� ��� flag�� off�ϰ� ���ÿ� �־ ����ó���� �ϰų� <>�� �����Ѵ�.
					if ((char)isc=='<') {
						
						flag=false;
						
					}
					
					//escape���ڸ� �����ڷ� �������ִ� ó�� ���ۺκ�
					if ((char)isc=='&') {
						
					//	System.out.println("escape����  ó������");
						
						flag=false;
						
					}
					
					//escape���ڸ� �˻��ؼ� ��ȯ���ִ� �б⹮
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
					//	outFile.write("��".getBytes());
						
					}
						
					
					//flag ���¿� ���� ��� ���ΰ� �����ȴ�.
					if (flag) {
						outFile.write(isc);
					}else {
						sb.append((char)isc);
					}
					
					
					
					if ((char)isc=='>') {
						
						flag=true;

					}
					
					//<script �϶� </script>�� �ɶ����� flag���� false�� �������ְ� sb�� ��Ƶξ������� ""���� �����ؼ� �ʱ�ȭ
					if (sb.indexOf("<script")>-1) {
						
						flag=false;
						
						sb.replace(0, sb.length(), "");
						
					}else if (sb.indexOf("</script>")>-1) {
						flag=true;
						sb.replace(0, sb.length(), "");
					}
					
					//script�±� ó���� �����ϰ� ó��
					if (sb.indexOf("<style")>-1) {
						
						flag=false;
						
						sb.replace(0, sb.length(), "");
						
					}else if (sb.indexOf("</style>")>-1) {
						flag=true;
						sb.replace(0, sb.length(), "");
					}
					
					


					
					
					//escape����ó���� ������ sb�� �ʱ�ȭ���ְ� ��� flag�� on
					if ((char)isc==';') {
						
					
						if (sb.indexOf("lt;")>-1) {
								flag=true;
								sb.replace(0, sb.length(), "");
							//	System.out.println("escape ó�� �Ϸ�");
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

				//	System.out.println("sb ����Ȯ��:"+sb);
						
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