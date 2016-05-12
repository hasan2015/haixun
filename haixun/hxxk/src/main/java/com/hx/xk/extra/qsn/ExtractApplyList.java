package com.hx.xk.extra.qsn;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.hx.xk.common.XkConstant;
import com.hx.xk.common.util.XkUtil;
import com.hx.xk.dto.DtoGrade;
import com.hx.xk.dto.DtoMygrade;
import com.hx.xk.dto.base.Pager;
import com.hx.xk.extra.Extract;
import com.hx.xk.extra.Rule;

public class ExtractApplyList {
	private static Log log = LogFactory.getLog(ExtractApplyList.class);

	/**
	 * 根据
	 * url::apply-front!applyList.action?stuId=93F4A7E02BB59B82E040007F01006CA0
	 * tag::class="tb" 获得已报班级列表
	 * 
	 * @param rule
	 * @return
	 */
	public static List<DtoMygrade> extract(String username, Rule rule) {
		Elements results = Extract.extract(username, rule);
		List<DtoMygrade> datas = new ArrayList<DtoMygrade>();
		DtoMygrade data = null;
//		DtoGrade grade = null;

		for (Element result : results) {
			Elements trs = result.getElementsByTag("tr");
			// 第一条记录是列头；其他是“班级列表”
			for (int i = 0; i < trs.size(); i++) {
				Element tr = trs.get(i);
				Elements links = tr.getElementsByTag("a");
				// 查看是否有班级链接，如果没有则是列头-跳过；其他则处理班级记录
				if (links != null && links.size() > 0) {
					// 取得“班级名”和“链接”
					Element link = links.get(0);
					data = new DtoMygrade();
//					grade = new DtoGrade();
					data.setLinkHref(link.attr("href"));
					data.setLinkText(link.text());
					//
					Elements tds = tr.getElementsByTag("td");
					// 班级其他字段值
					if (tds != null && tds.size() > 8) {
						// 预约号
						Element td = tds.get(0);
						if (td != null) {
							data.setReservedcode(XkUtil.clearNbsp(td.text()).trim());
						}
						// 过期时间
						td = tds.get(1);
						if (td != null) {
							data.setOvertime(XkUtil.clearNbsp(td.text()).trim());
						}
//						// 班级编号
//						td = tds.get(2);
//						if (td != null) {
//							grade.setCode(XkUtil.clearNbsp(td.text()).trim());
//						}
						// 报名状态
						td = tds.get(7);
						if (td != null) {
							data.setApplystatus(XkUtil.clearNbsp(td.text()).trim());
						}
					}
//					data.setYgrade(grade);
					datas.add(data);
				}

			}
		}

		return datas;
	}

	/**
	 * 根据： url:/apply-front.action?area=3 tag:class="tb" 获得某区域的可报班级列表
	 * 
	 * @param rule
	 * @return
	 */
	public static List<DtoGrade> extractOpenGrades(Rule rule) {
		Elements results = Extract.extract(XkConstant.V_EXTRACT_USERNAME_ANONYMOUS, rule);
		List<DtoGrade> datas = new ArrayList<DtoGrade>();
		DtoGrade data = null;

		for (Element result : results) {
			Elements trs = result.getElementsByTag("tr");
			// 第一条记录是列头；其他是“班级列表”
			for (int i = 0; i < trs.size(); i++) {
				Element tr = trs.get(i);
				Elements links = tr.getElementsByTag("a");
				// 查看是否有班级链接，如果没有则是列头-跳过；其他则处理班级记录
				if (links != null && links.size() > 0) {
					// 取得“班级名”和“链接”
					Element link = links.get(0);
					data = new DtoGrade();
					data.setLinkHref(link.attr("href"));
					data.setLinkText(link.text());
					//
					Elements tds = tr.getElementsByTag("td");
					// 班级其他字段值
					if (tds != null && tds.size() > 6) {
						// 班级状态
						Element td = tds.get(5);
						if (td != null) {
							data.setApplystatus(XkUtil.clearNbsp(td.text()).trim());
						}
					}

					datas.add(data);
				}

			}
		}

		log.info("extractOpenGrade.results.size=" + results.size() + " dtoGrade=" + data);
		return datas;
	}

	/**
	 * 根据： url:/apply-front.action?area=* tag:id="gopage" 获得某区域的可报班级列表的分页数据
	 * 
	 * @param rule
	 * @return
	 */
	public static Pager extractPager(Rule rule) {
		Elements results = Extract.extract(XkConstant.V_EXTRACT_USERNAME_ANONYMOUS, rule);
		Pager pager = new Pager();
		for (Element result : results) {
			List<TextNode> textNodes = result.textNodes();
			for (TextNode textnode : textNodes) {
				Pattern pattern = Pattern.compile("\\[.[0-9]+./.[0-9]+.\\]");
				Matcher matcher = pattern.matcher(textnode.text());
				if (matcher.find()) {
					String pagenode = matcher.group(0);
					pagenode = pagenode.substring(1, pagenode.length() - 1);
					String[] temp = pagenode.split("/");
					pager.setPageNow(XkUtil.isNumber(XkUtil.clearNbsp(temp[0]).trim())
							? Integer.parseInt(XkUtil.clearNbsp(temp[0]).trim()) : 1);
					pager.setPageSize(XkUtil.isNumber(XkUtil.clearNbsp(temp[1]).trim())
							? Integer.parseInt(XkUtil.clearNbsp(temp[1]).trim()) : 1);

					break;
				}

			}
		}
		log.info("extractPager.results.size=" + results.size() + " paget.size=" + pager.getPageSize());

		return pager;
	}
}
