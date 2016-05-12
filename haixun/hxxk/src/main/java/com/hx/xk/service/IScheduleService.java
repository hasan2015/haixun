/**
 * 
 */
package com.hx.xk.service;

import java.util.List;

import com.hx.xk.dto.DtoSchedule;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:22:19
 * 
 */
public interface IScheduleService extends IService<DtoSchedule> {
	/**
	 * 周次，课堂时间段，地点
	 * 这样查找课表，如果存在这4个条件都相同的课程时，就不能创建课表了--》bug
	 * 
	 * @param schedule
	 * @return
	 * @throws Exception
	 */
	List<DtoSchedule> retrieveByAllProps(DtoSchedule schedule) throws Exception;

	int deleteByGradeid(Integer gradeid) throws Exception;

	List<DtoSchedule> retrieveByGradeid(Integer gradeid) throws Exception;
}
