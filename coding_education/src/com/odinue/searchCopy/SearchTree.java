package com.odinue.searchCopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SearchTree {
	
	//root D:/java_tutorial/tutorial/
	
	static void searchDirectory(File rootFile,File childFile) {
		
		if (!rootFile.exists()) {
			System.out.println("제일 하위 디렉토리");
			return;
		}
		
		File[] fileList = rootFile.listFiles();
		
		System.out.println(fileList.length);
		for (int i = 0; i < fileList.length; i++) {
			
			if (fileList[i].isFile()) {
				
				String fileName=fileList[i].getName();
				int extIdx=fileName.lastIndexOf(".");
				String ext=fileName.substring(extIdx);
				
				byte[] writeFile=(fileName.substring(0, extIdx)+".txt").getBytes();
				
				
				if (ext==".html") {
				
					FileInputStream inputFile=null;
					FileOutputStream outFile=null;
					try {
						inputFile = new FileInputStream(fileList[i]);
						outFile = new FileOutputStream(childFile);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
					byte[] readByte=new byte[1024];
					
					int in = -1;
					long totBites = 0 ;
					try {
						while( (in=inputFile.read(readByte)) > 0){
							totBites+= in ;
							outFile.write(writeFile, 0, in);
							if ( totBites % 80==0) System.out.println(); ;
							System.out.println("[[[[[[[[[[[" + new String(writeFile,0,fileList.length) + "]]]]]]]]]]");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}else {
				System.out.println("파일이 아님."+i+"번째 index");
			}
			
			searchDirectory(fileList[i],childFile);

		}	
		
		
		
		

			
	}
		
		static void searchCopy(String path) {
		
			File root=new File(path);
			
			for (File childFile : root.listFiles()) {
				SearchTree.searchDirectory(root, childFile);
				
			}
			
			
			boolean isc=true;
			
//				File testFile=SearchTree.searchDirectory(root);
//				if (testFile==null) {
//					System.out.println("end");
//					isc=false;
//				}
			
		}
		
		
	//	System.out.println(file);
	
	public static void main(String[] args) {
		
	//	SearchTree.searchCopy(args[0]);
		
		String path="D:/searchTest/test/1";
		
		SearchTree.searchCopy(path);
		

	}

}
