package com.odinue.CopySearch;

import java.io.File;

import com.odinue.CopySearch.FileTraverse;


public class Html2Text_exam extends FileTraverse_exam.FileHandler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FileTraverse_exam ft = new FileTraverse_exam(new Html2Text_exam() ) ;
		//System.out.println(    ft.t(  new File( "D:\\searchTest") )  ) ;
		
		
	}

	@Override
	public int handle(File f) {
		// TODO Auto-generated method stub
		
		if ( f.getName().toUpperCase().endsWith(".HTML")) {
			System.out.println( f.getPath());
			return 1;
		}
		return 0;
	}

}