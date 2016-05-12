/**
 * 
 */
package com.hx.xk.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hx.xk.ctrl.GradeController;
import com.hx.xk.dto.DtoGrade;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.Pager;
import com.hx.xk.dto.mapper.MapperGrade;
import com.hx.xk.entity.Ygrade;
import com.hx.xk.service.IExtractQsnService;
import com.hx.xk.service.IGradeService;
import com.hx.xk.vo.VoOpengrade;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:23:43
 * 
 */
@Service("gradeService")
public class GradeServiceImpl extends AbstractService<Ygrade, DtoGrade> implements IGradeService {
	private Log log = LogFactory.getLog(GradeServiceImpl.class);
	@Resource
	private IExtractQsnService extractService;

	public GradeServiceImpl() {
		setEntityClass(Ygrade.class);
		setIdFieldName("gradeid");
		setConvertor(new MapperGrade());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newEntityInstance()
	 */
	@Override
	protected Ygrade newEntityInstance() {
		return new Ygrade();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newDtoInstance()
	 */
	@Override
	protected DtoGrade newDtoInstance() {
		return new DtoGrade();
	}

	@Override
	protected Map<String, Object> dto2Map(DtoGrade dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (dto.getCode() != null) {
			map.put("code", dto.getCode());
		}
		if (dto.getLink() != null) {
			map.put("link", dto.getLink());
		}
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hx.xk.service.IGradeService#retrieveByCode(java.lang.String)
	 */
	@Override
	@Transactional(value = "transactionManager")
	public DtoGrade retrieveDetail(DtoUser user, DtoGrade grade, boolean isRefresh) throws Exception {
		DtoGrade dtoGrade = null;
		if (isRefresh) {
			extractService.extractGrade(user, grade);
		}
		String fromString = "from Ygrade y where y.code='" + grade.getCode() + "'";
		String sqlString = "select y " + fromString;
		List<Ygrade> ly = defaultDao.findBySql(Ygrade.class, sqlString);
		if (ly != null && ly.size() > 0)
			dtoGrade = convertor.entity2Dto(ly.get(0));

		return dtoGrade;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hx.xk.service.IGradeService#retrieveDetail(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, com.hx.xk.dto.base.Pager)
	 */
	@Override
	public Pager retrieveOpengrades(String area, String[] weeks, String starttime, String endtime, String term,
			Pager pager) throws Exception {
		return retrieveOpengradeByParams(area, weeks, starttime, endtime, term, pager, "", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hx.xk.service.IGradeService#retrievebyParam(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, com.hx.xk.dto.base.Pager)
	 */
	@Override
	public Pager retrieveOpengradeByParams(String area, String[] weeks, String starttime, String endtime, String term,
			Pager pager, String applystatus, Integer specialtyid) throws Exception {
		String sql = "select g.gradeid,g.code,g.area,g.term,g.description,g.applystatus,g.agelimit,g.degree,"
				+ "s.specialtyid,s.name";
		String from = " from Ygrade g,Yspecialty s";
		String where = " where g.yspecialty.specialtyid=s.specialtyid";
		if ((weeks != null && weeks.length > 0) || (starttime != null && !starttime.isEmpty())
				|| (endtime != null && !endtime.isEmpty())) {
			from += ",Yschedule d";
			where += " and d.ygrade.gradeid=g.gradeid";
		}
		if (weeks != null && weeks.length > 0) {
			where += " and (";
			for (int i = 0; i < weeks.length; i++) {
				where += " week like '%" + weeks[i] + "%'";
				if (i < weeks.length - 1)
					where += " or";
			}
			where += ")";
		}
		if (starttime != null && !starttime.isEmpty()) {
			where += " and TIMESTAMPDIFF(MINUTE,CONCAT('2015-10-10 ','" + starttime
					+ "'),CONCAT('2015-10-10 ',d.starttime))>=0";// 不晚于starttime
		}
		if (endtime != null && !endtime.isEmpty()) {
			where += " and TIMESTAMPDIFF(MINUTE,CONCAT('2015-10-10 ','" + endtime
					+ "'),CONCAT('2015-10-10 ',d.endtime))<=0";// 不早于starttime
		}
		if (area != null && !area.isEmpty())
			where += " and g.area like '%" + area + "%'";
		if (term != null && !term.isEmpty())
			where += " and g.term like '%" + term + "%'";

		if (applystatus != null)
			if (applystatus.isEmpty())
				where += " and g.applystatus is not null";
			else
				where += " and g.applystatus like '%" + applystatus + "%'";
		if (specialtyid != null) {
			where += " and s.specialtyid=" + specialtyid;
		}
		List<VoOpengrade> vos = new ArrayList<VoOpengrade>();
		List<Object[]> lobj = defaultDao.findBySql(Object[].class, sql + from + where);
		if (lobj.size() > 0) {
			for (Object[] objects : lobj) {
				VoOpengrade vo = new VoOpengrade();
				vo.setGradeid(Integer.parseInt(objects[0].toString()));
				vo.setCode(objects[1].toString());
				vo.setArea(objects[2].toString());
				vo.setTerm(objects[3].toString());
				vo.setDescription(objects[4].toString());
				vo.setApplystatus(objects[5].toString());
				vo.setAgelimit(objects[6].toString());
				vo.setDegree(objects[7].toString());
				vo.setSpecialtyid(Integer.parseInt(objects[8].toString()));
				vo.setSpecialtyName(objects[9].toString());

				vos.add(vo);
			}
		}
		pager.setResult(vos);
		return pager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hx.xk.service.IGradeService#retrieveOpengradesBySpecialtyid(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, com.hx.xk.dto.base.Pager, int)
	 */
	@Override
	public Pager retrieveOpengradesBySpecialtyid(String area, String[] weeks, String starttime, String endtime,
			String term, Pager pager, Integer specialtyid) throws Exception {

		return retrieveOpengradeByParams(area, weeks, starttime, endtime, term, pager, "", specialtyid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hx.xk.service.IGradeService#retrieveByLinkAndCode(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public DtoGrade retrieveByLinkAndCode(String link, String code) throws Exception {
		DtoGrade dtoGrade = null;
		String sql = "select y from Ygrade y where y.link like '%" + link + "%' and y.code='" + code + "'";
		List<Ygrade> list = defaultDao.findBySql(Ygrade.class, sql);
		if (list != null && list.size() > 0) {
			dtoGrade = convertor.entity2Dto(list.get(0));
		}

		return dtoGrade;
	}

}
