package com.hx.xk.extra.qsn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hx.xk.common.XkConstant;
import com.hx.xk.common.util.XkUtil;
import com.hx.xk.dto.DtoGrade;
import com.hx.xk.dto.DtoSchedule;
import com.hx.xk.extra.Extract;
import com.hx.xk.extra.Rule;

public class ExtractClazzView {
	private static Log log = LogFactory.getLog(ExtractClazzView.class);

	/**
	 * 根据 url::apply-front!clazzView.action?clazzId=
	 * 282F73FDA5E04FD7ACCC4CC504BA6485 tag::class="tb"
	 * 查询班级”基本信息“；”课表信息“；”年级或年龄、性别要求“；”班级特征“
	 * 
	 * @param rule
	 * @return
	 */
	public static DtoGrade extract(String username, Rule rule) {
		Elements results = Extract.extract(username, rule);
		// List<DtoGrade> datas = new ArrayList<DtoGrade>();
		DtoGrade data = null;

		if (results != null && results.size() == 4) {
			data = new DtoGrade();
			Element result = results.get(0);
			// 基本信息
			Elements tds = result.getElementsByTag("td");
			if (tds != null && tds.size() > 8) {
				// 班级名称
				Element td = tds.get(0);
				if (td != null) {
					data.setCode(XkUtil.clearNbsp(td.text()).trim());
				}
				// 所属区域
				td = tds.get(1);
				if (td != null) {
					data.setArea(XkUtil.clearNbsp(td.text()).trim());
				}
				// 学年（索引+1，有标题）
				td = tds.get(3);
				if (td != null) {
					data.setYear(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(5);
				// 学期
				if (td != null) {
					data.setTerm(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(7);
				// 专业
				if (td != null) {
					data.setSpecialtyName(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(9);
				// 专业程度
				if (td != null) {
					data.setDegree(XkUtil.clearNbsp(td.text()).trim());
				}
				// 开课时间
				td = tds.get(11);
				if (td != null) {
					data.setStartdate(XkUtil.clearNbsp(td.text()).trim());
				}
				// 结课时间
				td = tds.get(13);
				if (td != null) {
					data.setEnddate(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(15);
				// 课次
				if (td != null) {
					data.setTimes(XkUtil.isNumber(XkUtil.clearNbsp(td.text()).trim())
							? Integer.parseInt(XkUtil.clearNbsp(td.text()).trim()) : 0);
				}
				td = tds.get(17);
				// 总费用
				if (td != null) {
					data.setCost(XkUtil.isNumber(XkUtil.clearNbsp(td.text()).trim())
							? Float.parseFloat(XkUtil.clearNbsp(td.text()).trim()) : 0.0f);
				}
				td = tds.get(19);
				// 班级类型
				if (td != null) {
					data.setType(XkUtil.clearNbsp(td.text()).trim());
				}
				// 咨询电话
				td = tds.get(21);
				if (td != null) {
					data.setPhone(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(23);
				// 费用明细
				if (td != null) {
					data.setCostdetail(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(25);
				// 班级描述
				if (td != null) {
					data.setDescription(XkUtil.clearNbsp(td.text()).trim());
				}
			}
			// 课表
			result = results.get(1);
			Elements pres = result.getElementsByTag("pre");
			if (pres != null && pres.size() > 0) {
				DtoSchedule schedule = null;
				for (Element pre : pres) {
					schedule = new DtoSchedule();
					String temp = pre.text().replace(" ", "");
					String[] schedules = temp.startsWith("周") ? temp.split("周") : temp.split("每");
					for (String s : schedules) {
						// 周四10:00-11:30发展中心213室
						// 1 23456789012345
						if (s.length() < 13)
							continue;
						schedule.setWeek(temp.startsWith("周") ? "周" + s.substring(0, 1) : "每天");
						schedule.setStarttime(s.substring(1, 6));
						schedule.setEndtime(s.substring(7, 12));// 空了一个“-”
						schedule.setAddress(s.substring(12, s.length()));

						data.getYschedules().add(schedule);
					}
				}
			}
			result = results.get(2);// 要求
			tds = result.getElementsByTag("td");
			if (tds != null && tds.size() > 0) {
				Element td = tds.get(1);
				// 学员年级范围
				if (td != null) {
					data.setAgelimit(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(3);
				// 性别要求
				if (td != null) {
					data.setGenderlimit(XkUtil.clearNbsp(td.text()).trim());
				}
			}
			result = results.get(3);// 特征
			tds = result.getElementsByTag("td");
			if (tds != null && tds.size() > 0) {
				Element td = tds.get(1);
				// 能力特征
				if (td != null) {
					data.setAbility(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(3);
				// 态度特征
				if (td != null) {
					data.setManner(XkUtil.clearNbsp(td.text()).trim());
				}
			}
			// datas.add(data);
		}

		return data;
	}

	public static DtoGrade extractOpenGrade(Rule rule) {
		Elements results = Extract.extract(XkConstant.V_EXTRACT_USERNAME_ANONYMOUS, rule);
		// List<DtoGrade> datas = new ArrayList<DtoGrade>();
		DtoGrade data = null;

		if (results != null && results.size() == 4) {
			data = new DtoGrade();
			Element result = results.get(0);
			// 基本信息
			Elements tds = result.getElementsByTag("td");
			if (tds != null && tds.size() > 8) {
				// 班级名称
				Element td = tds.get(0);
				if (td != null) {
					data.setCode(XkUtil.clearNbsp(td.text()).trim());
				}
				// 所属区域
				td = tds.get(1);
				if (td != null) {
					data.setArea(XkUtil.clearNbsp(td.text()).trim());
				}
				// 学年（索引+1，有标题）
				td = tds.get(3);
				if (td != null) {
					data.setYear(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(5);
				// 学期
				if (td != null) {
					data.setTerm(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(7);
				// 专业
				if (td != null) {
					data.setSpecialtyName(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(9);
				// 专业程度
				if (td != null) {
					data.setDegree(XkUtil.clearNbsp(td.text()).trim());
				}
				// 开课时间
				td = tds.get(11);
				if (td != null) {
					data.setStartdate(XkUtil.clearNbsp(td.text()).trim());
				}
				// 结课时间
				td = tds.get(13);
				if (td != null) {
					data.setEnddate(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(15);
				// 课次
				if (td != null) {
					data.setTimes(XkUtil.isNumber(XkUtil.clearNbsp(td.text()).trim())
							? Integer.parseInt(XkUtil.clearNbsp(td.text()).trim()) : 0);
				}
				td = tds.get(17);
				// 总费用
				if (td != null) {
					data.setCost(XkUtil.isNumber(XkUtil.clearNbsp(td.text()).trim())
							? Float.parseFloat(XkUtil.clearNbsp(td.text()).trim()) : 0.0f);
				}
				td = tds.get(19);
				// 班级类型
				if (td != null) {
					data.setType(XkUtil.clearNbsp(td.text()).trim());
				}
				// 咨询电话
				td = tds.get(21);
				if (td != null) {
					data.setPhone(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(23);
				// 费用明细
				if (td != null) {
					data.setCostdetail(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(25);
				// 班级描述
				if (td != null) {
					data.setDescription(XkUtil.clearNbsp(td.text()).trim());
				}
			}
			// 课表
			result = results.get(1);
			Elements pres = result.getElementsByTag("pre");
			if (pres != null && pres.size() > 0) {
				DtoSchedule schedule = null;
				for (Element pre : pres) {
					schedule = new DtoSchedule();
					String temp = pre.text().replace(" ", "");
					String[] schedules = temp.startsWith("周") ? temp.split("周") : temp.split("每");
					for (String s : schedules) {
						// 周四10:00-11:30发展中心213室
						// 1 23456789012345
						if (s.length() < 13)
							continue;
						schedule.setWeek(temp.startsWith("周") ? "周" + s.substring(0, 1) : "每天");
						schedule.setStarttime(s.substring(1, 6));
						schedule.setEndtime(s.substring(7, 12));// 空了一个“-”
						schedule.setAddress(s.substring(12, s.length()));

						data.getYschedules().add(schedule);
					}
				}
			}
			result = results.get(2);// 要求
			tds = result.getElementsByTag("td");
			if (tds != null && tds.size() > 0) {
				Element td = tds.get(1);
				// 学员年级范围
				if (td != null) {
					data.setAgelimit(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(3);
				// 性别要求
				if (td != null) {
					data.setGenderlimit(XkUtil.clearNbsp(td.text()).trim());
				}
			}
			result = results.get(3);// 特征
			tds = result.getElementsByTag("td");
			if (tds != null && tds.size() > 0) {
				Element td = tds.get(1);
				// 能力特征
				if (td != null) {
					data.setAbility(XkUtil.clearNbsp(td.text()).trim());
				}
				td = tds.get(3);
				// 态度特征
				if (td != null) {
					data.setManner(XkUtil.clearNbsp(td.text()).trim());
				}
			}
			// datas.add(data);
		}
		log.info("extractOpenGrade.results.size="+results.size()+" dtoGrade="+data);

		return data;
	}

}
