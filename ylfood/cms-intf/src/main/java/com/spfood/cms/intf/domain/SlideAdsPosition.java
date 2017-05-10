package com.spfood.cms.intf.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.spfood.kernel.domain.DomainObject;

public class SlideAdsPosition implements DomainObject {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column slide_ads_position.ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column slide_ads_position.name
     *
     * @mbggenerated
     */
    @NotNull
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column slide_ads_position.maxSlot
     *
     * @mbggenerated
     */
    private Integer maxSlot;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column slide_ads_position.minSlot
     *
     * @mbggenerated
     */
    @NotNull
    private Integer minSlot;
    
    private List<Advertisement> advertisements = new ArrayList<Advertisement>();

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column slide_ads_position.ID
     *
     * @return the value of slide_ads_position.ID
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column slide_ads_position.ID
     *
     * @param iD the value for slide_ads_position.ID
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column slide_ads_position.name
     *
     * @return the value of slide_ads_position.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column slide_ads_position.name
     *
     * @param name the value for slide_ads_position.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column slide_ads_position.maxSlot
     *
     * @return the value of slide_ads_position.maxSlot
     *
     * @mbggenerated
     */
    public Integer getMaxSlot() {
        return maxSlot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column slide_ads_position.maxSlot
     *
     * @param maxSlot the value for slide_ads_position.maxSlot
     *
     * @mbggenerated
     */
    public void setMaxSlot(Integer maxSlot) {
        this.maxSlot = maxSlot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column slide_ads_position.minSlot
     *
     * @return the value of slide_ads_position.minSlot
     *
     * @mbggenerated
     */
    public Integer getMinSlot() {
        return minSlot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column slide_ads_position.minSlot
     *
     * @param minSlot the value for slide_ads_position.minSlot
     *
     * @mbggenerated
     */
    public void setMinSlot(Integer minSlot) {
        this.minSlot = minSlot;
    }

	public List<Advertisement> getAdvertisements() {
		return advertisements;
	}

	public void setAdvertisements(List<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}
    
}