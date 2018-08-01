package com.odinue.searchCopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class SearchCopy_20180731 {
           
            static ArrayList<File> filePath=new ArrayList<File>();
           
            /**
             *
             * 1번.디렉토리 탐색해서 디렉토리 안에 html파일만 뽑아내어 array배열에 저장함.
             * test directory : d:/test
             * */
            public static ArrayList<File> searchDirectory(File childFile) {
                 
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
                               
                                fileName=file[i].getName();
                               
                                //확장자명(html)을 가져옴
                                fileExt=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
                               
                                //파일이고 확장자가 html인 파일이 있으면 배열에 담음.
                                if (file[i].isFile() && fileExt.equals("html")) {
                                     
                                      filePath.add(file[i]);
                                }
                         }
                        
                  }
                  return filePath;
                 
            }
           
           
            /**
             * html 태그발라내서 저장하기
             *
             */
			public static byte[] htmlParshing(String html) {
                 
                  StringBuilder outTxt = new StringBuilder();
                 
                  outTxt.append(html);
                  int start=0;
                  int end=0;
                 
                 
                 
/*                //주석 없애기
                  stIdx=outTxt.charAt(outTxt.indexOf("<!--"));
                  endIdx=outTxt.charAt(outTxt.indexOf("-->"));
                 
                  outTxt.delete(stIdx, endIdx+1);
        
                 
                  //스크립트부분 제거
                  stIdx=outTxt.charAt(outTxt.indexOf("<style type=\"text/css\">"));
                  endIdx=outTxt.charAt(outTxt.indexOf("</style>"));
                 
                  outTxt.delete(stIdx, endIdx+1);*/
                 
                 
                 //for문을 통해 태그 편집
                 ArrayList<String> htmlIdx=new ArrayList<>();
                
                 htmlIdx.add("<,>");
               //  htmlIdx.add("&,;"); //--> 이조건일 때는 앞에 있는 ;를 검색해서 처리하려다보니 exception이 발생
                
              //   htmlIdx.add("<!--,-->");
               //  htmlIdx.add("<style,</style>");
               //  htmlIdx.add("</");
                
                int cnt=100;
                 while (cnt>-1) {
                     
                           
                           
                           //태그안에 값을 살려야하는 태그에 대한 처리 : input
                           
                           
                           //태그안에 지워도 되는 태그에 대한 처리 : 
                           
                           
                           //단일태그에 대한 처리
                           
                           //&로 시작하는 태그
                    /*       if (htmlIdx.get(i).substring(0, 1).equals("&")) {
                                
                                 //열고 닫는 태그에 대한 처리
                                 start=outTxt.indexOf(htmlIdx.get(i).substring(htmlIdx.get(i).indexOf("&"), htmlIdx.get(i).indexOf(",")));
                                    end=outTxt.indexOf(htmlIdx.get(i).substring(htmlIdx.get(i).indexOf(start,",")+1))+1;
                                           
                                    System.out.println("startIdx: "+start+" / value: "+outTxt.charAt(start)+outTxt.charAt(start+1)+outTxt.charAt(start+2)+outTxt.charAt(start+3)+outTxt.charAt(start+4)+outTxt.charAt(start+5)+outTxt.charAt(start+6));
                                      System.out.println("endIdx: "+end+" / value: "+outTxt.charAt(end-1));
                                      System.out.println(i);
                                      
                                       if (start>0 && end>0 ) {
                                           
                                             outTxt.delete(start, end);
                                                
                                           }
                                      
                                       cnt--;
                                
                           } */
                    	   
                    	   
                           //주석에 대한 처리
                           if (html.compareTo("<!--")>-1) {

                        	   start=html.indexOf("<!--");
                               end=html.indexOf("-->")+3; 
                                    
                               System.out.println("주석처리 - startIdx: "+start+" / endIdx: "+end);
                               System.out.println("startIdx: "+start+" / value: "+html.charAt(start)+html.charAt(start+1)+html.charAt(start+2)+html.charAt(start+3)+html.charAt(start+4)+html.charAt(start+5)+html.charAt(start+6));
                               System.out.println("endIdx: "+end+" / value: "+html.charAt(end-1));
                           	
                               
                                if (start>=0 && end>0 ) {
                                    
                                       html.replace("<!--", "");
                                       html.replace("-->", "");
                                          
                                    }
                               
    
                               }  
                           
                           if (html.compareTo("<")>-1) {
                        	     
                               //열고 닫는 태그에 대한 처리
                               	start=html.indexOf("<");
                                   end=html.indexOf(">")+1; 
                                   
                                   if (start==-1 || end==-1) {
                                   	System.out.println("태그 탐색 오류 발생 ! startIdx: "+start+" / endIdx: "+end);
                                   	cnt=-1;
                                   	continue;
   								}
                                        
                                   System.out.println("태그처리 - startIdx: "+start+" / endIdx: "+end);
                               	
                                   
                                    if (start>=0 && end>0 ) {
                                        
                                        html.replace("<", "");
                                        html.replace(">", "");
                                              
                                        }
                                   
        
                                   }
                    	   
                           cnt--;
                           
                           //<가 첫글자로 들어간 태그들에 대한
                       /*     if (outTxt.equals(htmlIdx)) {
     
                            //열고 닫는 태그에 대한 처리
                            	start=outTxt.indexOf("<");
                                end=outTxt.indexOf(">")+1; 
                                
                                if (start==-1 || end==-1) {
                                	System.out.println("태그 탐색 오류 발생 ! startIdx: "+start+" / endIdx: "+end);
                                	cnt=-1;
                                	continue;
								}
                                     
                                System.out.println("태그처리 - startIdx: "+start+" / endIdx: "+end);
                                System.out.println("startIdx: "+start+" / value: "+outTxt.charAt(start)+outTxt.charAt(start+1)+outTxt.charAt(start+2)+outTxt.charAt(start+3)+outTxt.charAt(start+4)+outTxt.charAt(start+5)+outTxt.charAt(start+6));
                                System.out.println("endIdx: "+end+" / value: "+outTxt.charAt(end-1));
                            	
                                
                                 if (start>=0 && end>0 ) {
                                     
                                        outTxt.delete(start, end);
                                           
                                     }
                                
                                 cnt--;
     
                                } */
                           
                      
                      
                     }
                 
                  //input태그 제거 : text는 value값은 남겨두기,
                 
                  //
                
             //    byte[] bytes=new String(outTxt).getBytes();
                   byte[] bytes=html.getBytes();
                 
                
                 return bytes;
            }
           
                 
                 
                  /**
                   *
                   * 2..태그 삭제 및 태그안에 문자들만 발라내기
                   * html파일을 받아서 태그를 발라내서 파일로 반환하기
                   * */
                  public static byte[] fileEdit(File htmlFile) {
                        
                         String fileName = htmlFile.getName();
                         String filePath = htmlFile.getPath();
                         String path=filePath.substring(0,filePath.lastIndexOf("\\"));
                        
                         //파일명중에 .html을 빼고 .txt를 붙혀서 파일명 생성하기
                         String htmlExt=fileName.substring(0,fileName.lastIndexOf("."));
                         String fileFull=path+"\\"+htmlExt+".txt";
                        
                         FileInputStream inFile=null;
                         FileOutputStream outFile=null;
                         try {
                                inFile=new FileInputStream(htmlFile);
                                outFile=new FileOutputStream(fileFull);
                         } catch (FileNotFoundException e) {
                                e.printStackTrace();
                         }
                         byte[] readBytes=new byte[(int)htmlFile.length()];
                         int readCnt=0;
                         try {
                                readCnt=inFile.read(readBytes);
                                /**
                                 * 3번.txt파일의 html 태그들을 벗겨내고 페이지의 문자만 남겨서 파일로 저장하는 기능
                                 * 파일의 사이즈만큼 한번에 받아와서 저장 -> 일정 사이즈만큼 잘라서 처리하는 것과 퍼포먼스 비교 예정
                                 * 현재는 미구현 상태
                                 * */
                                String html=new String(readBytes,0,readBytes.length);
                               
                                byte[] outTxt=SearchCopy_20180731.htmlParshing(html);
                               
                                outFile.write(outTxt);
                        
                                     
                                System.out.println(fileFull+" : 저장완료");
                               
                                outFile.close();
                                outFile=null;
                                inFile.close();
                                inFile=null;
                               
                         } catch (IOException e) {
                                e.printStackTrace();
                         }finally {
                                try {
                                      if (outFile!=null) {
                                             outFile.close();
                                      }
                                      if (inFile!=null) {
                                             inFile.close();
                                      }
                                } catch (IOException e) {
                                      e.printStackTrace();
                                }
                         }
                        
                         return readBytes;
                  }
           
           
            public static void main(String[] args) {
                 
                  //1번
                  ArrayList<File> filePaths= SearchCopy_20180731.searchDirectory(new File(args[0]));
                  //System.out.println(filePaths);
                 
                  Iterator<File> iter=filePaths.iterator();
                 
                  while(iter.hasNext()) {
                        
                         //2번
                         byte[] sc=SearchCopy_20180731.fileEdit(iter.next());
                  //     System.out.println(new String(sc));
                        
                  }
                 
                 
                 
                 
            }
          
}
