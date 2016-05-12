/**
 * 
 */
package com.hx.xk.dto;

import java.sql.Timestamp;

import com.hx.xk.dto.base.DtoBase;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:24:12
 * 
 */
public class DtoMyclock extends DtoBase implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1376023826757744640L;
	private Integer myclockid;
	private DtoMygrade ymygrade;
	private String name;
	private Timestamp starttime;
	private Timestamp endtime;
	private Integer intervalm;
	private Integer duration;

	/**
	 * @return the myclockid
	 */
	public Integer getMyclockid() {
		return myclockid;
	}

	/**
	 * @param myclockid
	 *            the myclockid to set
	 */
	public void setMyclockid(Integer myclockid) {
		this.myclockid = myclockid;
	}

	/**
	 * @return the ymygrade
	 */
	public DtoMygrade getYmygrade() {
		return ymygrade;
	}

	/**
	 * @param ymygrade
	 *            the ymygrade to set
	 */
	public void setYmygrade(DtoMygrade ymygrade) {
		this.ymygrade = ymygrade;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the starttime
	 */
	public Timestamp getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime
	 *            the starttime to set
	 */
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	/**
	 * @return the endtime
	 */
	public Timestamp getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime
	 *            the endtime to set
	 */
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return the intervalm
	 */
	public Integer getIntervalm() {
		return intervalm;
	}

	/**
	 * @param intervalm
	 *            the intervalm to set
	 */
	public void setIntervalm(Integer intervalm) {
		this.intervalm = intervalm;
	}

	/**
	 * @return the duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

}
