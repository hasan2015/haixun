package com.hx.xk.extra.qsn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hx.xk.common.util.XkUtil;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.extra.Extract;
import com.hx.xk.extra.Rule;

public class ExtractRegisterfront {
	private static Log log = LogFactory.getLog(ExtractRegisterfront.class);

	/**
	 * 根据
	 * url::http://hzcs.qsng.cn/hz-bsp/hz/register-front!edit.action；tag::class=
	 * "login" 抓取“已报班级url”，“及个人信息url”
	 * 
	 * @param rule
	 * @return
	 */
	public static DtoUser extract(String username, Rule rule) {
		Elements results = Extract.extract(username, rule);

		DtoUser user = new DtoUser();

		if (results.size() > 0) {
			Element result = results.get(0);
			Elements links = result.getElementsByAttributeValue("name", "studentForm.name");
			if (links != null && links.size() > 0) {
				Element link = links.get(0);
				user.setName(link.attr("value"));//name
			}
			links = result.getElementsByAttributeValue("name", "studentForm.homeTel");
			if (links != null && links.size() > 0) {
				Element link = links.get(0);
				user.setHomephone(link.attr("value"));//
			}
			links = result.getElementsByAttributeValue("name", "studentForm.parentName");
			if (links != null && links.size() > 0) {
				Element link = links.get(0);
				user.setPatriarch(link.attr("value"));//
			}
			links = result.getElementsByAttributeValue("name", "studentForm.school");
			if (links != null && links.size() > 0) {
				Element link = links.get(0);
				user.setSchool(link.attr("value"));//
			}
			links = result.getElementsByAttributeValue("name", "studentForm.genderName");
			if (links != null && links.size() > 0) {
				Element link = links.get(0);
				user.setGender(XkUtil.clearNbsp(link.attr("value")).trim());//
			}
			links = result.getElementsByTag("select");//.getElementsByAttributeValue("id", "studentForm.grade");
			if (links != null && links.size() > 0) {
				Element select = links.get(0);
				Elements options=select.getElementsByAttributeStarting("selected");
				Element option=options.get(0);
				user.setGrade(option.attr("title"));//
			}
			links = result.getElementsByAttributeValue("name", "studentForm.birthday");
			if (links != null && links.size() > 0) {
				Element link = links.get(0);
				user.setBirthday(link.attr("value"));//
			}
			links = result.getElementsByAttributeValue("name", "studentForm.memo");
			if (links != null && links.size() > 0) {
				Element link = links.get(0);
				user.setDescription(link.attr("value"));//
			}
		}

		log.info("extract.opengradeaa=" + user.getName());
		return user;

	}

}
