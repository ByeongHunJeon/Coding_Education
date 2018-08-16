package com.odinue.CopySearch;

import java.io.File;

public class FileTraverse {
	
	public FileTraverse() {
	}

	public  static abstract class FileHandler {
		public abstract int handle( File f) ;
	}
	FileHandler handler = null;
	
	public FileTraverse(FileHandler handler) {
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

		return sum ;
	}
}