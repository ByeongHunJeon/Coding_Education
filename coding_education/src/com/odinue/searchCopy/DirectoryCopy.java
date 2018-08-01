package com.odinue.searchCopy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class DirectoryCopy {
     
              
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
                  *
                  * 3.html 태그발라내서 저장하기
                  *
                  */
                  public static StringBuilder htmlParshing(byte[] html) {
                    
                       StringBuilder outTxt = new StringBuilder();
                    
                       outTxt.append(html);
                       int start=0;
                       int end=0;
                    
             
                    
                      int cnt=100;
                       while (html.length!=-1) {
                         
                               System.out.println(html.length);
                               
                                 //스크립트
                                 if (outTxt.indexOf("<script")>-1) {
                                   
                                       start=outTxt.indexOf("<script");
                                       end=outTxt.indexOf("</script>")+8;
                                             
                                        System.out.println("startIdx: "+start+" / endIdx: "+end);
                                        System.out.println("script start value: "+outTxt.charAt(start)+outTxt.charAt(start+1)+outTxt.charAt(start+2)+outTxt.charAt(start+3)+outTxt.charAt(start+4)+outTxt.charAt(start+5)+outTxt.charAt(start+6));
                                        System.out.println("script end value: "+outTxt.charAt(end-9)+outTxt.charAt(end-8)+outTxt.charAt(end-7)+outTxt.charAt(end-6)+outTxt.charAt(end-5)+outTxt.charAt(end-4)+outTxt.charAt(end-3)+outTxt.charAt(end-2)+outTxt.charAt(end-1)+outTxt.charAt(end)+outTxt.charAt(end+1));
                                        System.out.println("endIdx: "+end+" / value: "+outTxt.charAt(end));
                                        
                                        if (end>-1) {
                                            
                                        	outTxt.delete(start, outTxt.length());
                                        	
                                        }
                                        
                                           if (start>=0 && end>0 ) {
                                             
                                                 outTxt.delete(start, end+1);
                                                  
                                           }
                                  
                                 }
                                
                                
                                 //css
                                 if (outTxt.indexOf("<style")>-1) {
                                   
                                       start=outTxt.indexOf("<style");
                                       end=outTxt.indexOf("</style>")+8;
                                       
                                             
                                        System.out.println("startIdx: "+start+" / endIdx: "+end);
                                        System.out.println("script start value: "+outTxt.charAt(start)+outTxt.charAt(start+1)+outTxt.charAt(start+2)+outTxt.charAt(start+3)+outTxt.charAt(start+4)+outTxt.charAt(start+5)+outTxt.charAt(start+6));
                                        System.out.println("script end value: "+outTxt.charAt(end-9)+outTxt.charAt(end-8)+outTxt.charAt(end-7)+outTxt.charAt(end-6)+outTxt.charAt(end-5)+outTxt.charAt(end-4)+outTxt.charAt(end-3)+outTxt.charAt(end-2)+outTxt.charAt(end-1)+outTxt.charAt(end)+outTxt.charAt(end+1));
                                        System.out.println("endIdx: "+end+" / value: "+outTxt.charAt(end));
                                        
                                        if (end>-1) {
                                        
                                        	outTxt.delete(start, outTxt.length());
                                        	
                                        }
                                        
                                        if (start>=0 && end>0 ) {
                                             
                                              outTxt.delete(start, end+1);
                                                 
                                        }
                                        
                                  
                                 }
                               
                               
                               
                                 //주석 : api문서로 작업시에는 StringIndexOutOfBoundsException발생
                                /* if (outTxt.indexOf("<!--")>-1) {
                                      
                                   
                                     //열고 닫는 태그에 대한 처리
                                       start=outTxt.indexOf("<!--");
                                       end=outTxt.indexOf("-->")+2;
                                             
                                        System.out.println("startIdx: "+start+" / endIdx: "+end);
                                        //System.out.println("주석 value: "+outTxt.charAt(start)+outTxt.charAt(start+1)+outTxt.charAt(start+2)+outTxt.charAt(start+3)+outTxt.charAt(start+4)+outTxt.charAt(start+5)+outTxt.charAt(start+6));
                                          System.out.println("endIdx: "+end+" / value: "+outTxt.charAt(end));
                                        
                                           if (start>=0 && end>0 ) {
                                             
                                                 outTxt.delete(start, end+1);
                                                  
                                           }
                                        
                                  
                               } */
                               
                               
                                 //&로 시작하는 태그
                                  if (outTxt.indexOf("&")>-1) {
                                      
                                    
                                       //열고 닫는 태그에 대한 처리
                                              start=outTxt.indexOf("&");
                                              end=outTxt.indexOf(";",start);
                                               
                                          System.out.println("startIdx: "+start+" / endIdx: "+end);
                                          System.out.println("value: "+outTxt.charAt(start)+outTxt.charAt(start+1)+outTxt.charAt(start+2)+outTxt.charAt(start+3)+outTxt.charAt(start+4)+outTxt.charAt(start+5)+outTxt.charAt(start+6));
                                          System.out.println("endIdx: "+end+" / value: "+outTxt.charAt(end));
                                            
                                          if (end>-1) {
                                                
                                          	outTxt.delete(start, outTxt.length());
                                           	
                                          }
                                          
                                          if (start>=0 && end>0 ) {
                                               
                                                outTxt.delete(start, end+1);
                                                    
                                          }
                                          
                                    
                                 }
                               
                                 //<가 첫글자로 들어간 태그들에 대한
                                  if (outTxt.indexOf("<")>-1) {
         
                                  //열고 닫는 태그에 대한 처리
                                 // start=outTxt.indexOf(htmlIdx.get(i).substring(0, htmlIdx.get(i).indexOf(",")));
                                  //start 지점부터 지정한 문자열를 찾아서 해당 index를 반환한다.
                                 // end=outTxt.indexOf(htmlIdx.get(i).substring(htmlIdx.get(i).indexOf(",")+1),start)+1;
                                        start=outTxt.indexOf("<");
                                        end=outTxt.indexOf(">",start);
                                         
                                     System.out.println("startIdx: "+start+" / endIdx: "+end);
                                     //System.out.println("태그 value: "+outTxt.charAt(start)+outTxt.charAt(start+1)+outTxt.charAt(start+2)+outTxt.charAt(start+3)+outTxt.charAt(start+4)+outTxt.charAt(start+5)+outTxt.charAt(start+6));
                                     System.out.println("endIdx: "+end+" / value: "+outTxt.charAt(end));
                                    
                                     if (end>-1) {
                                         
                                     	outTxt.delete(start, outTxt.length());
                                     	
                                     }
                                     
                                       if (start>=0 && end>0 ) {
                                         
                                              outTxt.delete(start, end+1);
                                               
                                           }
                                    
         
                                      }
                               
                          
                             cnt--;
                          
                           }
                     
                        //input태그 제거 : text는 value값은 남겨두기,
                     
                        //
                    
                    
                       return outTxt;
                  }
                    
                    
                       /**
                        *
                        * 2.태그 삭제 및 태그안에 문자들만 발라내기
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
                             // byte[] readBytes=new byte[(int)htmlFile.length()];
                              byte[] readBytes=new byte[1024];
                              
                              int readCnt=0;
                              try {
                                     readCnt=inFile.read(readBytes);
                                     /**
                                      * 3번.txt파일의 html 태그들을 벗겨내고 페이지의 문자만 남겨서 파일로 저장하는 기능
                                      * 파일의 사이즈만큼 한번에 받아와서 저장 -> 일정 사이즈만큼 잘라서 처리하는 것과 퍼포먼스 비교 예정
                                      * 현재는 미구현 상태
                                      * */
                                     StringBuilder txt=null;
                                     
                                     int idx=-1;
                                     while ((idx=inFile.read(readBytes)) !=-1) {
										
                                    	 System.out.println(readCnt);
                                      	 txt=DirectoryCopy.htmlParshing(readBytes);
                                         txt.append(txt);
                                  
                                     }
                                     
                                     byte[] outTxt=new String(txt).getBytes(); 

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
                       ArrayList<File> filePaths= DirectoryCopy.searchDirectory(new File(args[0]));
                       //System.out.println(filePaths);
                    
                       Iterator<File> iter=filePaths.iterator();
                    
                       while(iter.hasNext()) {
                           
                              //2번
                              byte[] sc=DirectoryCopy.fileEdit(iter.next());
                       //     System.out.println(new String(sc));
                           
                       }
                    
                    
                    
                    
                 }
             
}