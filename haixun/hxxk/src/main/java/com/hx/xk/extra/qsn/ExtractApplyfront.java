package com.hx.xk.extra.qsn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hx.xk.extra.Extract;
import com.hx.xk.extra.Rule;

public class ExtractApplyfront {
	private static Log log = LogFactory.getLog(ExtractApplyfront.class);

	/**
	 * 根据
	 * url::http://hzcs.qsng.cn/hz-bsp/hz/apply-front.action；tag::class="login"
	 * 抓取“已报班级url”，“及个人信息url”
	 * 
	 * @param rule
	 * @return
	 */
	public static String[] extract(String username, Rule rule) {
		Elements results = Extract.extract(username, rule);

		String opengrade = null, myinfo = null;
		if (results.size() > 0) {
			Element result = results.get(0);
			Elements links = result.getElementsByTag("a");
			if (links != null && links.size() > 1) {
				Element link = links.get(0);//stuId
				opengrade = link.attr("href");
				link = links.get(1);//Id
				myinfo = link.attr("href");
			}
		}
		log.info("extract.opengrade=" + opengrade + " myinfo=" + myinfo);
		return new String[] { opengrade, myinfo };

	}

}
