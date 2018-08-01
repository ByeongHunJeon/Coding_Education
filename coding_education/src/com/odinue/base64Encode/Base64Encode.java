package com.odinue.base64Encode;

public class Base64Encode {

	void base64Encode() {
    	byte[] bsrc="Man".getBytes();
       
    	byte[] table="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".getBytes();
       
        int src= bsrc[0] << 16 | bsrc[1] << 8 | bsrc[2] ;
       
        byte[] target=new byte[4];
       
        for ( int i =0 ; i < target.length ; i++ ) {
           target[i]=table[ (src << (8 + (6*i)) >> 26) & 0x0000003f ];
        }
        System.out.println(new String(target));

	}
	
	public static void main(String[] args) {
		Base64Encode en=new Base64Encode();
		en.base64Encode();
	}
}
