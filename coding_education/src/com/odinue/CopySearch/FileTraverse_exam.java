package com.odinue.CopySearch;

import java.io.File;

public class FileTraverse_exam {

	public  static abstract class FileHandler {
		public abstract int handle( File f) ;
	}
	FileHandler handler = null;
	
	public FileTraverse_exam(FileHandler handler) {
		this.handler = handler; 
	}
	
	public int t(File f ) {
		if ( f.isFile()) {
			if ( handler == null ) System.out.println ( f.getPath() );
			else return handler.handle(f);
			
		}
		
		File[] fs = f.listFiles() ;
		
		int sum = 0 ;
		
		for ( int i = 0 ; i < fs.length ; i++ ) {
			
			sum += t( fs[i] ) ;
		}

		String.format("KB카드 %20s 에서 %d 사용 ", "홍대점", 1200);
				
		return sum ;
	}
	
	
}