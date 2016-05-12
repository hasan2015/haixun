/**
 * 
 */
package com.hx.xk;

import java.text.ParseException;
import java.util.Date;

import com.hx.xk.common.XkConstant;

/**
 * @author Hasan
 * @Date 2016年3月19日 下午3:00:43
 *
 */
public class tt {

	public static void main(String[] args) throws ParseException {

		Date cDate=new Date();
		System.err.println(cDate.getTime());
//		String temp=cDate.getYear()+""+(cDate.getMonth()+1)+cDate.getDate();
		String temp=XkConstant.SDF_yyyyMMdd.format(cDate);
		System.err.println(temp);
		cDate=XkConstant.SDF_yyyyMMdd.parse(temp);
		System.err.println(temp);
		System.err.println(cDate.getTime());
		//cDate=XkConstant.SDF_yyyyMMdd.parse(temp);
		
	}
}
