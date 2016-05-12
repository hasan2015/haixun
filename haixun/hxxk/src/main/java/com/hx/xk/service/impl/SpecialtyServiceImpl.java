/**
 * 
 */
package com.hx.xk.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hx.xk.dto.DtoSpecialty;
import com.hx.xk.dto.base.Pager;
import com.hx.xk.dto.mapper.MapperSpecialty;
import com.hx.xk.entity.Yspecialty;
import com.hx.xk.service.ISpecialtyService;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:23:43
 * 
 */
@Service("specialtyService")
public class SpecialtyServiceImpl extends AbstractService<Yspecialty, DtoSpecialty>implements ISpecialtyService {
	private Log log = LogFactory.getLog(SpecialtyServiceImpl.class);
	public SpecialtyServiceImpl() {
		setEntityClass(Yspecialty.class);
		setIdFieldName("specialtyid");
		setConvertor(new MapperSpecialty());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newEntityInstance()
	 */
	@Override
	protected Yspecialty newEntityInstance() {
		return new Yspecialty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newDtoInstance()
	 */
	@Override
	protected DtoSpecialty newDtoInstance() {
		return new DtoSpecialty();
	}

	@Override
	protected Map<String, Object> dto2Map(DtoSpecialty dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (dto.getName() != null) {
			map.put("name", dto.getName());
		}

		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hx.xk.service.ISpecialtyService#retrieveSpecialtyOfOpengrade()
	 */
	@Override
	public List<DtoSpecialty> retrieveSpecialtyOfOpengrade(Pager pager) throws Exception {
		return retrieveSpecialtyOfOpengrade(null, null, null, null, null, pager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hx.xk.service.ISpecialtyService#retrieveSpecialtyOfOpengrade(java.
	 * lang.String, java.lang.String, java.lang.String[], java.lang.String,
	 * java.lang.String, com.hx.xk.dto.base.Pager)
	 */
	@Override
	public List<DtoSpecialty> retrieveSpecialtyOfOpengrade(String area, String term, String[] weeks, String starttime,
			String endtime, Pager pager) throws Exception {
		List<DtoSpecialty> lDtoSpecialties = new ArrayList<DtoSpecialty>();
		String sql = "select s.specialtyid,s.name,count(s.specialtyid)";
		String from = " from Yspecialty s,Ygrade g";
		String where = " where g.yspecialty.specialtyid=s.specialtyid" + " and g.applystatus is not null";
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

		sql += from + where + " group by s.specialtyid";
		log.info(" retrieveSpecialtyOfOpengrade.sql="+sql);
		List<Object[]> lobj = defaultDao.findBySql(Object[].class, sql, pager);
		// if (lobj.size() > 0) {
		for (Object[] objects : lobj) {
			DtoSpecialty dtoSpecialty = new DtoSpecialty();
			dtoSpecialty.setSpecialtyid(Integer.parseInt(objects[0].toString()));
			dtoSpecialty.setName(objects[1].toString());
			dtoSpecialty.setGradecount(Integer.parseInt(objects[2].toString()));

			lDtoSpecialties.add(dtoSpecialty);
		}
		// }

		return lDtoSpecialties;
	}

}
