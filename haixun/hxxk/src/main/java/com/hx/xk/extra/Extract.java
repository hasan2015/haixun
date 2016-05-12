package com.hx.xk.extra;

/**
 * 
 */

/**
 * @author Hasan
 * @Date 2015-5-23 上午9:35:54
 *
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hx.xk.common.util.StringUtils;

public class Extract {
	private static Log log = LogFactory.getLog(Extract.class);

	/**
	 * @param rule
	 * @return
	 */
	public static Elements extract(String username, Rule rule) {
		// 进行对rule的必要校验
		validateRule(rule);
		Elements results = new Elements();
		try {
			// 解析rule
			String url = rule.getUrl();
			String[] params = rule.getParams();
			String[] values = rule.getValues();
			String resultTagName = rule.getResultTagName();
			int type = rule.getType();
			int requestType = rule.getRequestMoethod();

			Connection conn = Jsoup.connect(url);
			// 设置查询参数
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					conn.data(params[i], values[i]);
				}
			}
			Map<String, String> cMap = new HashMap<String, String>();
			cMap.put("JSESSIONID", HttpRequest.getSessionMap().get(username));
			cMap.put("babyface", "Yes");
			conn.cookies(cMap);// .cookie(cMap);
			// 设置请求类型
			Document doc = null;
			switch (requestType) {
			case Rule.GET:
				doc = conn.timeout(100000).get();
				break;
			case Rule.POST:
				doc = conn.timeout(100000).post();
				break;
			}
			// 处理返回数据
			// Elements results = new Elements();
			switch (type) {
			case Rule.CLASS:
				// tb=班级列表
				results = doc.getElementsByClass(resultTagName);
				break;
			case Rule.ID:
				Element result = doc.getElementById(resultTagName);
				results.add(result);
				break;
			case Rule.SELECTION:
				results = doc.select(resultTagName);
				break;
			default:
				// 当resultTagName为空时默认去body标签
				if (StringUtils.isNull(resultTagName)) {
					results = doc.getElementsByTag("body");
				}
			}
			log.info("username=" + username + " rule.url=" + rule.getUrl());
		} catch (IOException e) {
			log.error("extract::" + e.getMessage());
		}
		return results;
	}

	/**
	 * 对传入的参数进行必要的校验
	 */
	private static void validateRule(Rule rule) {
		String url = rule.getUrl();
		if (StringUtils.isNull(url)) {
			throw new RuleException("url不能为空！");
		}
		if (!url.startsWith("http://")) {
			throw new RuleException("url的格式不正确！");
		}

		if (rule.getParams() != null && rule.getValues() != null) {
			if (rule.getParams().length != rule.getValues().length) {
				throw new RuleException("参数的键值对个数不匹配！");
			}
		}

	}

}
