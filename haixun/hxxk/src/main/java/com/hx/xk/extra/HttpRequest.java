package com.hx.xk.extra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hx.xk.common.XkConstant;
import com.hx.xk.dto.base.DtoResult;

public class HttpRequest {
	// 自动存储session过期判断
	public static Map<String, String> sessionMap = new HashMap<String, String>();
	public static Map<String, Long> durationMap = new HashMap<String, Long>();

	/**
	 * @return the sessionMap
	 */
	public static Map<String, String> getSessionMap() {
		return sessionMap;
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String cmd, String param, String name) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString =  cmd + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			String session_value = sessionMap.get(name);
			if (session_value != null)
				connection.setRequestProperty("Cookie", session_value);
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				// System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static DtoResult sendPost(String cmd, String param, String name) {
		PrintWriter out = null;
		BufferedReader in = null;
		DtoResult result = new DtoResult();
		result.setCode(XkConstant.RESULT_CODE_USER_NOT_LOGIN);
		try {
			URL realUrl = new URL(cmd);
			// 打开和URL之间的连接
			// URLConnection conn = realUrl.openConnection();
			// 解决取不到cookie问题
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
					// "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT
					// 5.1;SV1)");
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
			String session_value = sessionMap.get(name);
			if (session_value != null) {
				conn.setRequestProperty("Cookie", session_value);
				result.setCode(XkConstant.RESULT_CODE_SUCCESS);
			}
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 解决取不到cookie问题
			conn.setInstanceFollowRedirects(false);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			// while ((line = in.readLine()) != null) {
			// result += line;
			// }
			// if (session_value == null) {
			session_value = conn.getHeaderField("Set-Cookie");
			// result = conn.getHeaderField("Cookie");
			String[] tempStrings = session_value.split(";");
			if (tempStrings.length > 1)
				if (tempStrings[0] != null && tempStrings[0].split("=").length > 1) {
					session_value = tempStrings[0].split("=")[1];
					sessionMap.put(name, session_value);
					durationMap.put(name, System.currentTimeMillis());
					result.setCode(XkConstant.RESULT_CODE_SUCCESS);
				}
			// System.err.println("session:=" + result);
			// }
			result.setResult(session_value);
		} catch (Exception e) {
			result.setCode(XkConstant.RESULT_CODE_FAILURE);
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				result.setCode(XkConstant.RESULT_CODE_FAILURE);
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {

		// 发送 POST 请求
		// String sr = HttpRequest.sendPost(
		// "http://localhost:8080/ddsmms/page/clinic/login.do",
		// "accountname=doctor100&password=123456");
		// System.out.println(sr);
		// HttpRequest.login("doctor100", "123456");

		// 发送 GET 请求
		String s = HttpRequest.sendGet("http://localhost:8080/ddsmms/page/clinic/retrieveDoctorDetail.do",
				"key=123&v=456", "doctor100");
		System.out.println("----1-----" + s);
		// 发送 GET 请求
		s = HttpRequest.sendGet("http://localhost:8080/ddsmms/page/clinic/retrieveDoctorDetail.do", "key=123&v=456",
				"doctor100");
		System.out.println("----2-----" + s);
	}
}