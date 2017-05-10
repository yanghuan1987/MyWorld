package com.spfood.pms.intf.domain;

import com.spfood.kernel.domain.DomainObject;

public class CommodityPicture implements DomainObject {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_picture.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_picture.commodity_code
     *
     * @mbggenerated
     */
    private Commodity commodity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_picture.picture_show_position
     *
     * @mbggenerated
     */
    private Integer pictureShowPosition;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_picture.picture_show_order
     *
     * @mbggenerated
     */
    private Integer pictureShowOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_picture.picture_size
     *
     * @mbggenerated
     */
    private String pictureSize;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_picture.picture_property
     *
     * @mbggenerated
     */
    private String pictureProperty;
    
    /**
     * 图片位置
     */
    private String pictureUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_picture.id
     *
     * @return the value of pms_commodity_picture.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_picture.id
     *
     * @param id the value for pms_commodity_picture.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_picture.picture_show_position
     *
     * @return the value of pms_commodity_picture.picture_show_position
     *
     * @mbggenerated
     */
    public Integer getPictureShowPosition() {
        return pictureShowPosition;
    }

    public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_picture.picture_show_position
     *
     * @param pictureShowPosition the value for pms_commodity_picture.picture_show_position
     *
     * @mbggenerated
     */
    public void setPictureShowPosition(Integer pictureShowPosition) {
        this.pictureShowPosition = pictureShowPosition;
    }

    public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_picture.picture_show_order
     *
     * @return the value of pms_commodity_picture.picture_show_order
     *
     * @mbggenerated
     */
    public Integer getPictureShowOrder() {
        return pictureShowOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_picture.picture_show_order
     *
     * @param pictureShowOrder the value for pms_commodity_picture.picture_show_order
     *
     * @mbggenerated
     */
    public void setPictureShowOrder(Integer pictureShowOrder) {
        this.pictureShowOrder = pictureShowOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_picture.picture_size
     *
     * @return the value of pms_commodity_picture.picture_size
     *
     * @mbggenerated
     */
    public String getPictureSize() {
        return pictureSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_picture.picture_size
     *
     * @param pictureSize the value for pms_commodity_picture.picture_size
     *
     * @mbggenerated
     */
    public void setPictureSize(String pictureSize) {
        this.pictureSize = pictureSize == null ? null : pictureSize.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_picture.picture_property
     *
     * @return the value of pms_commodity_picture.picture_property
     *
     * @mbggenerated
     */
    public String getPictureProperty() {
        return pictureProperty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_picture.picture_property
     *
     * @param pictureProperty the value for pms_commodity_picture.picture_property
     *
     * @mbggenerated
     */
    public void setPictureProperty(String pictureProperty) {
        this.pictureProperty = pictureProperty == null ? null : pictureProperty.trim();
    }
}