package com.hx.xk.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	  /*** 
 * MD5加码 生成32位md5码 
 */  
	public static String string2MD5(String inStr){  
		
	    MessageDigest md5 = null;  
	    try{  
	        md5 = MessageDigest.getInstance("MD5");  
	    }catch (Exception e){  
	        System.out.println(e.toString());  
	        e.printStackTrace();  
	        return "";  
	    }  
	    
	    char[] charArray = inStr.toCharArray();  
	    byte[] byteArray = new byte[charArray.length];  
	
	    for (int i = 0; i < charArray.length; i++)  
	        byteArray[i] = (byte) charArray[i];  
		    byte[] md5Bytes = md5.digest(byteArray);  
		    StringBuffer hexValue = new StringBuffer();  
	    for (int i = 0; i < md5Bytes.length; i++){  
	        int val = ((int) md5Bytes[i]) & 0xff;  
	        if (val < 16)  
	            hexValue.append("0");  
	        hexValue.append(Integer.toHexString(val));  
	    }  
	    return hexValue.toString();  
	}  

	/** 
	 * 加密解密算法 执行一次加密，两次解密 
	 */   
	public static String convertMD5(String inStr){  
	
	    char[] a = inStr.toCharArray();  
	    for (int i = 0; i < a.length; i++){  
	        a[i] = (char) (a[i] ^ 't');  
	    }  
	    String s = new String(a);  
	    return s;  
	}  
	
	/**
     * 加密算法
     * @param word
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static final String encrypt(String word){
        String hexResult = "";
        String[] tabStringHex = new String[16];
        byte[] data = new byte[0];
        MessageDigest md =null;
        try {
			md = MessageDigest.getInstance("MD5");
			data = word.getBytes("US-ASCII");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte[] result = md.digest(data);
        for (int i = 0; i < result.length; i++)
        {
            int v = result[i] & 0xFF;
            tabStringHex[i] = Integer.toHexString(v);
            hexResult += tabStringHex[i];
        }
        return hexResult;
    }
}