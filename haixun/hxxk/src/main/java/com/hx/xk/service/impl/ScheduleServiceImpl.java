/**
 * 
 */
package com.hx.xk.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hx.xk.dto.DtoSchedule;
import com.hx.xk.dto.mapper.MapperSchedule;
import com.hx.xk.entity.Yschedule;
import com.hx.xk.service.IScheduleService;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:23:43
 * 
 */
@Service("scheduleService")
public class ScheduleServiceImpl extends AbstractService<Yschedule, DtoSchedule> implements IScheduleService {
	public ScheduleServiceImpl() {
		setEntityClass(Yschedule.class);
		setIdFieldName("scheduleid");
		setConvertor(new MapperSchedule());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newEntityInstance()
	 */
	@Override
	protected Yschedule newEntityInstance() {
		return new Yschedule();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newDtoInstance()
	 */
	@Override
	protected DtoSchedule newDtoInstance() {
		return new DtoSchedule();
	}

	@Override
	protected Map<String, Object> dto2Map(DtoSchedule dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (dto.getScheduleid() != null) {
			map.put("scheduleid", dto.getScheduleid());
		}
		if (dto.getWeek() != null) {
			map.put("week", dto.getWeek());
		}
		if (dto.getStarttime() != null) {
			map.put("starttime", dto.getStarttime());
		}
		if (dto.getEndtime() != null) {
			map.put("endtime", dto.getEndtime());
		}
		if (dto.getAddress() != null) {
			map.put("address", dto.getAddress());
		}
		// if (dto.getYgrade() != null) {
		// if (dto.getYgrade().getGradeid() != null) {
		// map.put("gradeid", dto.getYgrade().getGradeid());
		// }
		// }
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hx.xk.service.IScheduleService#retrieveByAllProps(com.hx.xk.dto.
	 * DtoSchedule)
	 */
	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public List<DtoSchedule> retrieveByAllProps(DtoSchedule schedule) throws Exception {
		List<DtoSchedule> ldto = new ArrayList<DtoSchedule>();
		String sqlString = "select s from Yschedule s,Ygrade g where s.ygrade.gradeid=g.gradeid" + " and s.week ='"
				+ schedule.getWeek() + "' and s.starttime='" + schedule.getStarttime() + "' and s.endtime='"
				+ schedule.getEndtime() + "' and s.address='" + schedule.getAddress() + "' and s.ygrade.gradeid="
				+ schedule.getYgrade().getGradeid();

		List<Yschedule> lSchedules = defaultDao.findBySql(Yschedule.class, sqlString);
		if (lSchedules != null && lSchedules.size() > 0) {
			for (Yschedule s : lSchedules) {
				DtoSchedule dto = convertor.entity2Dto(s);
				ldto.add(dto);
			}
		}

		return ldto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hx.xk.service.IScheduleService#deleteByGradeid(java.lang.Integer)
	 */
	@Override
	@Transactional
	public int deleteByGradeid(Integer gradeid) throws Exception {
		String sql = "delete from Yschedule y where y.ygrade.gradeid=" + gradeid;
		return defaultDao.executeQuery(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hx.xk.service.IScheduleService#retrieveByGradeid(java.lang.Integer)
	 */
	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public List<DtoSchedule> retrieveByGradeid(Integer gradeid) throws Exception {
		String sql = "select s from Yschedule s where s.ygrade.gradeid=" + gradeid;
		
		List<DtoSchedule> ldto=new ArrayList<DtoSchedule>();
		List<Yschedule> lSchedules = defaultDao.findBySql(Yschedule.class, sql);
		if (lSchedules != null && lSchedules.size() > 0) {
			for (Yschedule s : lSchedules) {
				DtoSchedule dto = convertor.entity2Dto(s);
				ldto.add(dto);
			}
		}

		return ldto;
	}

}
