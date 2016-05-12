package com.hx.xk.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.xerces.impl.xpath.regex.ParseException;

/**
 * 处理字符串相关工具类
 * 
 * @author songyuanming
 */
public class StringUtils {
	
	/**
	 * 空字符串判断, <span style="color:red;">去左右空格后</span>, 如果为空返回 true<br/>
	 * 
	 * @param param
	 * @return boolean
	 */
	public static boolean isNull(CharSequence param) {
		return (param == null || "".equals(param.toString().trim()));
	}
	
	/**
	 * 空字符串判断, <span style="color:red;">去左右空格后</span>, 如果不为空返回 true<br/>
	 * 
	 * @param param
	 * @return
	 */
	public static boolean isNotNull(CharSequence param) {
		return !isNull(param);
	}
	
	/**
	 * 集合若为空 或 长度为0, 返回 true
	 * 
	 * @param list : 集合
	 * @return boolean
	 */
	public static boolean isNull(Collection<? extends Object> list) {
		return (list == null || list.size() == 0);
	}
	
	/**
	 * 集合若为空 或 长度为 0, 返回 false
	 * 
	 * @param list : 集合
	 * @return boolean
	 */
	public static boolean isNotNull(Collection<? extends Object> list) {
		return !isNull(list);
	}
	
	/**
	 * 检查字符串去左右空格后是否超出指定长度, 超出则返回 true. 为空也返回 true
	 * 
	 * @param param : <span style="color:red;">注意去左右空格</span>
	 * @param min : 最小长度
	 * @param max : 最大长度
	 * @return boolean
	 */
	public static boolean checkLength(CharSequence param, int min, int max) {
		return isNull(param) ? true : (param.length() < min || param.length() > max);
	}
	
	/**
	 * 检测 email 格式, 匹配则返回 true<br/>
	 * 格式: 下划线 数字或字母 @ 字母 数字或中横线 . 2 到 4 个字母或数字<br/><br/>
	 * 如: _@a.b , a@-.b , a@b.c.d 均匹配;  -@a.b , a@_.b , a@b.cdefg 均不匹配
	 * 
	 * @param email
	 * @return boolean
	 */
	public static boolean checkEmail(String email) {
		// ^[a-zA-Z0-9_\\.\\-]+\\@([a-zA-Z0-9\\-]+\\.)+[a-zA-Z0-9]{2,4}$
		String regex = "^\\w\\S*@([a-zA-Z0-9\\-]+\\.)+[a-zA-Z0-9]{2,4}$";
		return checkRegexWithStrict(email, regex);
	}
	
	/**
	 * 验证 指定正则 是否 <span style="color:red;">全字匹配</span> 指定字符串, 匹配则返回 true <br/>
	 * <br/>
	 * 左右空白符 : (?m)(^\s*|\s*$)<br/>
	 * 帐号输入(字母或数字开头, 长度 5-16, 可以有下划线) : ^[a-zA-Z0-9]\\w{4,15}$<br/>
	 * 手机 : ^1(3[0-9]|5[0|3|5|6|7|8|9]|8[6|8|9])[0-9]{8}$<br/>
	 * 空白符 : (^\\s*)|(\\s*$)<br/>
	 * IP : ([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])(\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])){3}<br/>
	 * 日期 : (([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)
	 * 匹配多行注释 : /\*\*(\s|.)*?\* /<br/>
	 * 
	 * .....
	 * 
	 * @param param
	 * @param regex
	 * @return boolean
	 */
	public static boolean checkRegexWithStrict(CharSequence param, String regex) {
		return isNull(param) ? false : Pattern.compile(regex).matcher(param).matches();
	}
	
	/**
	 * 
	  * 检测手机格式
	  * @author zhuchaofan
	  * @date 2015-8-16
	  * @param phone
	  * @return
	 */
	public static boolean isPhone(String phone){
		return checkRegexWithStrict(phone,"(\\+\\d+)?1[34578]\\d{9}$");
	}
	/**
	 * 检测 中文, 包含中文则返回 true
	 * 
	 * @param chinese
	 * @return boolean
	 */
	public static boolean checkChinese(CharSequence chinese) {
		return checkRegexWithRelax(chinese, "[\\u4e00-\\u9fa5]");
	}
	
	/**
	 * 此模式为非严格型匹配, <span style="color:red;">只要找到匹配即返回 true</span><br/>
	 * <br/>
	 * 中文: [\\u4e00-\\u9fa5]<br/>
	 * ....
	 * 
	 * @param param
	 * @param regex
	 * @return boolean
	 */
	public static boolean checkRegexWithRelax(CharSequence param, String regex) {
		return isNull(param) ? false : Pattern.compile(regex).matcher(param).find();
	}
	
	/**
	 * 日期类型以何种格式转换成字符串类型, 异常则返回 "exception"
	 * 
	 * @param date java.sql.Date 和 java.sql.Timestamp 均可
	 * @param param : 默认格式 "yyyy-MM-dd HH:mm"
	 * @return String
	 */
	public static String getStringFromDate(Date date, String param) {
		if (date == null) return null;
		try {
			param = isNull(param) ? "yyyy-MM-dd HH:mm" : param;
			return new SimpleDateFormat(param).format(date);
		} catch (Exception e) {
			return "exception";
		}
	}
	
	/**
	 * 字符串类型以何种格式转换成日期类型, 异常则返回 null
	 * 
	 * @param date : 格式 like "2000-01-01 01:01:01"
	 * @param param : 若为空则默认格式为 "yyyy-MM-dd HH:mm:ss"
	 * @return Date
	 */
	public static Date getDateFromString(String date, String param) {
		try {
			param = isNull(param) ? "yyyy-MM-dd HH:mm:ss" : param;
			return new SimpleDateFormat(param).parse(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 验证文件后缀: 如果包含此后缀则返回 true<br/><br/>example:<br/>
	 * <span style="color:red;">List&lt;String&gt; filetypes = Arrays.asList("jpg", "gif", "zip", "gz" ...);<br/>
	 * boolean flag = testpostfix("abc.jpg", filetypes);</span>
	 * 
	 * @param filename : 文件名
	 * @param fileTypes : 文件类型 list, 默认是 jpg, bmp, gif, zip, gz
	 * @return
	 */
	public static boolean testpostfix(String filename, List<String> fileTypes) {
		fileTypes = isNull(fileTypes)
			? Arrays.asList("jpg", "bmp", "gif", "zip", "gz")
			: fileTypes;
		
		// 得到文件尾数 并 进行小写转换
		String postfix = filename.substring(filename.lastIndexOf(".") + 1)
				.toLowerCase();
		return fileTypes.contains(postfix);
	}
	
	/**
	 * 验证后缀名, 如果包含此后缀则返回 true<br/><br/>example:<br/>
	 * <span style="color:red;">boolean flag = testpostfix("abc.jpg", "jpg, bmp, gif");</span>
	 * 
	 * @param filename : 文件名
	 * @param fitype : 文件类型, 以 ", " 隔开. 默认是 zip, gz, jpg, bmp, gif
	 * @return
	 */
	public static boolean testpostfix(String filename, String fitype) {
		fitype = StringUtils.isNull(fitype)
			? "zip, gz, jpg, bmp, gif" 
			: fitype.replace("\n", "").replace("\r", "").replace("\t", "");
		
		String[] type = fitype.split(",");
		for (int i = 0; i < type.length; i++)
			type[i] = type[i].trim();
		
		return testpostfix(filename, Arrays.asList(type));
	}

	/**
	 * 将 webservices 中的日期对象转换为 Date 对象
	 * 
	 * @param date
	 * @return
	 */
	public static Date convertToDate(XMLGregorianCalendar date) {
		if (date == null) return null;
		
		return date.toGregorianCalendar().getTime();
	}
	
	/**
	 * 将 Date 类型转换为 Webservices 对象
	 * 
	 * @param date
	 * @return
	 */
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
		if (date == null) return null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		try {
			return DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(
						calendar.get(Calendar.YEAR),
						calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DAY_OF_MONTH),
						calendar.get(Calendar.HOUR_OF_DAY),
						calendar.get(Calendar.MINUTE),
						calendar.get(Calendar.SECOND),
						calendar.get(Calendar.MILLISECOND),
						calendar.get(Calendar.ZONE_OFFSET) / (1000 * 60));
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 将 汉字 转换为 UTF-8 形式
	 * 
	 * @param str
	 * @return UTF-8 形式 String(like: %E4%B8%80)
	 */
	public static String toUtf8String(String str) {
		if (isNull(str)) return "";
		
		// Java 有封装同样效果的方法
		// return java.net.URLEncoder.encode(str, "utf-8");
		
		StringBuilder sbd = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch >= 0 && ch <= 255) {
				sbd.append(ch);
			} else {
				byte[] b;
				try {
					b = String.valueOf(ch).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sbd.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sbd.toString();
	}
	
	/**
	 * 将 UTF-8 格式的字符转换为汉字
	 * 
	 * @param str
	 * @return 汉字
	 */
	public static String unescape(String str) {
		if (isNull(str)) return "";
		
		// Java 有封装同样效果的方法
		// return java.net.URLDecoder.decode(str, "utf-8");
		
		StringBuilder sbd = new StringBuilder();
		int ch = -1;
		int b, sumb = 0;
		for (int i = 0, more = -1; i < str.length(); i++) {
			switch (ch = str.charAt(i)) {
				case '%':
					ch = str.charAt(++i);
					int hb = (Character.isDigit((char) ch) ? ch - '0'
							: 10 + Character.toLowerCase((char) ch) - 'a') & 0xF;
					ch = str.charAt(++i);
					int lb = (Character.isDigit((char) ch) ? ch - '0'
							: 10 + Character.toLowerCase((char) ch) - 'a') & 0xF;
					b = (hb << 4) | lb;
					break;
				case '+':
					b = ' ';
					break;
				default:
					b = ch;
			}
			if ((b & 0xc0) == 0x80) {
				sumb = (sumb << 6) | (b & 0x3f);
				if (--more == 0)
					sbd.append((char) sumb);
			} else if ((b & 0x80) == 0x00) {
				sbd.append((char) b);
			} else if ((b & 0xe0) == 0xc0) {
				sumb = b & 0x1f;
				more = 1;
			} else if ((b & 0xf0) == 0xe0) {
				sumb = b & 0x0f;
				more = 2;
			} else if ((b & 0xf8) == 0xf0) {
				sumb = b & 0x07;
				more = 3;
			} else if ((b & 0xfc) == 0xf8) {
				sumb = b & 0x03;
				more = 4;
			} else /* if ((b & 0xfe) == 0xfc) */{
				sumb = b & 0x01;
				more = 5;
			}
		}
		return sbd.toString();
	}
	
	public static String StringDatePlus1(String date) throws Exception{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dd = df.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		calendar.add(Calendar.DAY_OF_MONTH, 1);//加一天
		date = df.format(calendar.getTime());
		return date;
	}
	
	public static Integer StringDateToAge(Timestamp date) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal=Calendar.getInstance();  
		long l = cal.getTimeInMillis();
		long a = l-date.getTime();
		long b = a/1000/60/60/24;//换算为天
		int age = (int)(b/365);
		return age;
	}
	
	
	public static String IDCardValidate(String IDStr) throws ParseException {
		String errorInfo = "";// 记录错误信息
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		String Ai = "";
		// ================ 号码的长度 15位或18位 ================
		if (IDStr.length() != 15 && IDStr.length() != 18) {
			errorInfo = "身份证号码长度应该为15位或18位。";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 数字 除最后以为都为数字 ================
		if (IDStr.length() == 18) {
			Ai = IDStr.substring(0, 17);
		} else if (IDStr.length() == 15) {
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		}
		if (isNumeric(Ai) == false) {
			errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 出生年月是否有效 ================
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份
		if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
			errorInfo = "身份证生日无效。";
			return errorInfo;
		}
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
					|| (gc.getTime().getTime() - s.parse(
							strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
				errorInfo = "身份证生日不在有效范围。";
				return errorInfo;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
			errorInfo = "身份证月份无效";
			return errorInfo;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
			errorInfo = "身份证日期无效";
			return errorInfo;
		}
		// =====================(end)=====================

		// ================ 地区码时候有效 ================
		Hashtable h = GetAreaCode();
		if (h.get(Ai.substring(0, 2)) == null) {
			errorInfo = "身份证地区编码错误。";
			return errorInfo;
		}
		// ==============================================

		// ================ 判断最后一位的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi
					+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;

		if (IDStr.length() == 18) {
			if (Ai.equals(IDStr) == false) {
				errorInfo = "身份证无效，不是合法的身份证号码";
				return errorInfo;
			}
		} else {
			return "";
		}
		// =====================(end)=====================
		return "";
	}

	/**
	 * 功能：设置地区编码
	 * 
	 * @return Hashtable 对象
	 */
	@SuppressWarnings("unchecked")
	private static Hashtable GetAreaCode() {
		Hashtable hashtable = new Hashtable();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}

	/**
	 * 功能：判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能：判断字符串是否为日期格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDate(String strDate) {
		Pattern pattern = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(strDate);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
}
