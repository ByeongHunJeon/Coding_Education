package com.odinue.searchCopy;

import java.io.File;
import java.util.ArrayList;

public class SearchDirectory {
	
	static ArrayList<File> filePath=new ArrayList<>();


	public static ArrayList<File> searchDirectory(File childFile) {
		
//		1.디렉토리 탐색해서 디렉토리 안에 html파일만 뽑아내어 배열에 담아둠
//		->재귀함수, io.file.exist(),file.listFile(), collection(ArrayList)
		//test directory : d:/test
		//File root=new File("d:/test");
		String fileName="";
		String fileExt="";
		
		
		if (childFile.exists()) {
			File[] file=childFile.listFiles();
			
			//파일이고 .html이면 배열에 저장, 디렉토리면 하위 디렉토리 탐색
			for (int i = 0; i < file.length; i++) {
				
				
				if (file[i].isDirectory()) {
					
					if (file[i].exists()) {
						searchDirectory(file[i]);
					}else {
						continue;
					}
					
					
				}
				//Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: -1
				fileName=file[i].getName();
				
				//확장자명만 걸러냄
				fileExt=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
				
				//& 썼을 때 if문을 타지않음
				if (file[i].isFile() && fileExt.equals("html")) {
				//	System.out.println(file[i]);
					
					// path가 첫디렉토리의 path만 담김
					filePath.add(file[i]);
				}
			}
			
		}
		return filePath;
		
	}
		

//		2.태그 삭제 및 태그안에 문자들만 발라내기

	

//		3.txt파일로 저장하기
//		->fileOutputStream
		
		
	
	
	public static void main(String[] args) {
		
		//1번 
		ArrayList<File> filePaths= SearchCopy.searchDirectory(new File(args[0]));
		System.out.println(filePaths);
		
		
		
		
	}

}