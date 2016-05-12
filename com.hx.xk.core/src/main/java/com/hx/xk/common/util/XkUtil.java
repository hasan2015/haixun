/**
 * 
 */
package com.hx.xk.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.hx.xk.common.XkConstant;

/**
 * 
 * @author Hasan
 * @date 2014-2-2 下午5:06:26
 * 
 */
public class XkUtil {
	public static SimpleDateFormat SDF_yyyyMMdd = new SimpleDateFormat(
			"yyyyMMdd");
	public static SimpleDateFormat SDF_yyyy_MM_dd_ = new SimpleDateFormat(
			"yyyy年MM月dd日");
	public static SimpleDateFormat SDF_yyyy_MM_dd = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static SimpleDateFormat SDF_yyMMddHHmm = new SimpleDateFormat(
			"yyMMddHHmm");
	public static SimpleDateFormat SDF_yyyyMMddHHmmssSSS = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");
	public static SimpleDateFormat SDF_yyyyMMddHHmmss = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat SDF_HHmm = new SimpleDateFormat(
			"HH点mm分");

	/**
	 * 
	 * @param property
	 * @param propertyvalue
	 * @return
	 */
	public static String getFuzzyProperty(String property, Object propertyvalue) {
		if (propertyvalue.toString().contains(XkConstant.FUZZY_PROPERTY_PREFIX)
				|| propertyvalue.toString()
						.contains(XkConstant.FUZZY_UNDERLINE)
				|| propertyvalue.toString().startsWith(
						XkConstant.FUZZY_NOTLINKE))
			property = XkConstant.FUZZY_PROPERTY_PREFIX + property;
		return property;
	}

	/**
	 * 
	 * @return
	 */
	public static String getLocalHostIp() {
		String[] ret = null;
		try {
			String hostName = InetAddress.getLocalHost().getHostAddress();
			if (hostName.length() > 0) {
				InetAddress[] addrs = InetAddress.getAllByName(hostName);
				if (addrs.length > 0) {
					ret = new String[addrs.length];
					for (int i = 0; i < addrs.length; i++) {
						ret[i] = addrs[i].getHostAddress();
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			ret = null;
		}

		for (String s : ret) {
			System.err.println(s);
		}

		return ret[0];

	}

	/**
	 * 
	 * @param neip
	 *            网关ip
	 * @return
	 * @throws SocketException
	 */
	public static String getAdapterInternalIP(String neip)
			throws SocketException {
		Map<String, Integer> mapIP = new HashMap<String, Integer>();
		String[] neips = neip.split("\\.");
		String re = null;

		Enumeration<NetworkInterface> list = NetworkInterface
				.getNetworkInterfaces();
		for (; list.hasMoreElements();) {
			NetworkInterface nint = list.nextElement();
			// System.out.println("Net Card: " + nint.getDisplayName());
			for (Enumeration<InetAddress> addresses = nint.getInetAddresses(); addresses
					.hasMoreElements();) {
				InetAddress i_adr = addresses.nextElement();
				String ip = i_adr.getHostAddress();
				// System.out.println("------->" + ip);
				String[] ips = ip.split("\\.");
				if (ips != null && ips.length == 4) {
					int equal = 0;
					for (int i = 0; i < 4; i++) {
						if (neips[i].equals(ips[i]))
							equal++;
						else {
							break;
						}
					}
					mapIP.put(ip, equal);
				}
			}
		}
		int equal = 0;
		for (String ip : mapIP.keySet()) {
			if (equal < mapIP.get(ip)) {
				re = ip;
				equal = mapIP.get(ip);
			}
		}
		return re;

	}

	public static String genVerifyCode(int len) {
		String s = "";
		for (int i = 0; i < len; i++) {
			s += new Random().nextInt(10);
		}
		return s;
	}

	public static String getFileExtension(String fileName) {
		// String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}
	}

	public static void main(String[] args) {
		try {
			;
			System.err.println(XkUtil.getAdapterInternalIP("192.168.1.1"));
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断字符串是否是整数
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否是浮点数
	 */
	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			if (value.contains("."))
				return true;
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否是数字
	 */
	public static boolean isNumber(String value) {
		return isInteger(value) || isDouble(value);
	}

	/**
	 * 根据生日获取年龄
	 */
	public static short getAge(Timestamp birth) {
		Long year = (new Date().getTime() - birth.getTime())
				/ (365L * 24L * 60L * 60L * 1000L);
		return year.shortValue();
	}

	/**
	 * 隐藏姓名(显示第一个字符，之后显示**)
	 */
	public static String getChangeName(String name) {
		return name.trim().substring(0, 1) + "**";
	}

	/**
	 * 年月日+传过来的时分秒 把String 转换成 Tmpimesta
	 */
	public static Timestamp strdate(Timestamp timestamp, String approvedtime) {
		long time = timestamp.getTime();
		Date date = new Date(time);
		String newdate = XkConstant.SDF_yyyy_MM_dd.format(date) + " "
				+ String.valueOf(approvedtime);
		Timestamp t = Timestamp.valueOf(newdate);
		return t;

	}

	/**
	 * 根据日期取星期 星期天-星期六 0-6
	 */

	public static int getday(Timestamp timestamp) {
		int day = 0;
		Calendar cal = Calendar.getInstance();
		Date d = new Date(timestamp.getTime());
		cal.setTime(d);
		int calday = cal.get(Calendar.DAY_OF_WEEK);
		if (calday == Calendar.MONDAY)
			day = 1;
		if (calday == Calendar.TUESDAY)
			day = 2;
		if (calday == Calendar.WEDNESDAY)
			day = 3;
		if (calday == Calendar.THURSDAY)
			day = 4;
		if (calday == Calendar.FRIDAY)
			day = 5;
		if (calday == Calendar.SATURDAY)
			day = 6;
		if (calday == Calendar.SUNDAY)
			day = 0;
		return day;
	}

	/**
	 * 通过星期 取最近的日期
	 * 
	 * @param week
	 * @return
	 */
	public static String getWeek(Short week) {
		String dates = "";
		Calendar cal = Calendar.getInstance();
		int date = cal.get(Calendar.DAY_OF_MONTH);
		int n = cal.get(Calendar.DAY_OF_WEEK);
		int m = Integer.valueOf(week);// 要返回的星期数
		if (n == 1) {
			n = 7;
		} else {
			n = n - 1;
		}
		// 日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (m <= n) {
			cal.set(Calendar.DAY_OF_MONTH, date + m + 7 - n);
			dates = sdf.format(cal.getTime());
		} else {
			cal.set(Calendar.DAY_OF_MONTH, date + m - n);
			dates = sdf.format(cal.getTime());
		}
		return dates;
	}

	public static String clearNbsp(String str) {
		if (str.indexOf("\u00A0") > 0) {
			str = str.replaceAll("\u00A0", "");
		}
		return str;
	}
}
