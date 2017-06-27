package com.spfood.pms.search.intf.domain.criteria;

import com.spfood.kernel.domain.DomainObject;
import com.spfood.pms.search.intf.domain.Commodity;

public class CommodityAndComment extends Commodity implements DomainObject {
	private static final long serialVersionUID = 1L;
	
    /**
     * 好评条数 
     */
    private Integer goodComment;
    
    /**
     * 中评数
     */
    private Integer mediumComment;
    
    /**
     * 差评数
     */
    private Integer badComment;

	/**
	 * @return the goodComment
	 */
	public Integer getGoodComment() {
		return goodComment;
	}

	/**
	 * @param goodComment the goodComment to set
	 */
	public void setGoodComment(Integer goodComment) {
		this.goodComment = goodComment;
	}

	/**
	 * @return the mediumComment
	 */
	public Integer getMediumComment() {
		return mediumComment;
	}

	/**
	 * @param mediumComment the mediumComment to set
	 */
	public void setMediumComment(Integer mediumComment) {
		this.mediumComment = mediumComment;
	}

	/**
	 * @return the badComment
	 */
	public Integer getBadComment() {
		return badComment;
	}

	/**
	 * @param badComment the badComment to set
	 */
	public void setBadComment(Integer badComment) {
		this.badComment = badComment;
	}
    
}