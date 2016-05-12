/**
 * 
 */
package com.hx.xk.weixin.test;


/**
 * @author Hasan
 * @Date 2015-8-19 下午2:26:16
 * 
 */
public class test {
	public static void main(String[] args) {
		// System.err.println(MD5.encrypt("oAxETwn4waToWub74E9Xlxs6gbGw"));
		String state = "bp0108";
		String menu = state.substring(0, state.indexOf("0"));
		String tcode = state.substring(state.indexOf("0") + 1);
		System.err.println(menu);
		System.err.println(tcode);
		
	}

}
