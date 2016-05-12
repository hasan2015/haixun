/**
 * 
 */
package com.hx.xk.vo;

/**
 * 
 * @author Hasan
 * @Date 2015年11月30日 上午9:07:21
 *
 */
public class VoYear implements java.io.Serializable {
	private Integer mygradeid;
	private String year;
	private Integer yearCount;

	/**
	 * @return the mygradeid
	 */
	public Integer getMygradeid() {
		return mygradeid;
	}

	/**
	 * @param mygradeid
	 *            the mygradeid to set
	 */
	public void setMygradeid(Integer mygradeid) {
		this.mygradeid = mygradeid;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the yearCount
	 */
	public Integer getYearCount() {
		return yearCount;
	}

	/**
	 * @param yearCount the yearCount to set
	 */
	public void setYearCount(Integer yearCount) {
		this.yearCount = yearCount;
	}
 

}
