/**
 * 
 */
package com.spfood.pms.search.intf.domain.criteria;

import java.util.List;

import com.spfood.kernel.domain.DomainObject;

/**
 * @author Administrator
 *
 */
public class Parameters implements DomainObject {
	private static final long serialVersionUID = 1L;
	
	private List<String> listParameters;
	
	private Integer integerParameters;
	
	private String strParameters;
	
	/**
	 * @return the listParameters
	 */
	public List<String> getListParameters() {
		return listParameters;
	}

	/**
	 * @param listParameters the listParameters to set
	 */
	public void setListParameters(List<String> listParameters) {
		this.listParameters = listParameters;
	}

	/**
	 * @return the integerParameters
	 */
	public Integer getIntegerParameters() {
		return integerParameters;
	}

	/**
	 * @param integerParameters the integerParameters to set
	 */
	public void setIntegerParameters(Integer integerParameters) {
		this.integerParameters = integerParameters;
	}

	/**
	 * @return the strParameters
	 */
	public String getStrParameters() {
		return strParameters;
	}

	/**
	 * @param strParameters the strParameters to set
	 */
	public void setStrParameters(String strParameters) {
		this.strParameters = strParameters;
	}

	/**
	 * @return the longInteger
	 */
	public Long getLongInteger() {
		return longInteger;
	}

	/**
	 * @param longInteger the longInteger to set
	 */
	public void setLongInteger(Long longInteger) {
		this.longInteger = longInteger;
	}

	private Long longInteger;

}
