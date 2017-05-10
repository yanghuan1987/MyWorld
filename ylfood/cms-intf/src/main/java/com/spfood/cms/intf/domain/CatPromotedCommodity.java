package com.spfood.cms.intf.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.spfood.kernel.domain.DomainObject;

public class CatPromotedCommodity implements DomainObject {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cat_promoted_commodity.ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cat_promoted_commodity.cat_promote_pos_id
     *
     * @mbggenerated
     */
    @Min(1)
    private Long catPromotePosId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cat_promoted_commodity.commodity_code
     *
     * @mbggenerated
     */
    @Size(min=1,max=16)
    private String commodityCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cat_promoted_commodity.product_code
     *
     * @mbggenerated
     */
    @Size(min=1,max=16)
    private String productCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cat_promoted_commodity.commodity_name
     *
     * @mbggenerated
     */
    @Size(min=1,max=128)
    private String commodityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cat_promoted_commodity.commodity_spec
     *
     * @mbggenerated
     */
    @Size(min=1,max=16)
    private String commoditySpec;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cat_promoted_commodity.commodity_img_url
     *
     * @mbggenerated
     */
    @Size(min=1,max=256)
    private String commodityImgUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cat_promoted_commodity.display_order
     *
     * @mbggenerated
     */
    @Min(1)
    @Max(100)
    private Integer displayOrder;

    private CatPromotePos catPromotePos;
    
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cat_promoted_commodity.ID
     *
     * @return the value of cat_promoted_commodity.ID
     *
     * @mbggenerated
     */
    public Long getiD() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cat_promoted_commodity.ID
     *
     * @param iD the value for cat_promoted_commodity.ID
     *
     * @mbggenerated
     */
    public void setiD(Long iD) {
        this.id = iD;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cat_promoted_commodity.cat_promote_pos_id
     *
     * @return the value of cat_promoted_commodity.cat_promote_pos_id
     *
     * @mbggenerated
     */
    public Long getCatPromotePosId() {
        return catPromotePosId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cat_promoted_commodity.cat_promote_pos_id
     *
     * @param catPromotePosId the value for cat_promoted_commodity.cat_promote_pos_id
     *
     * @mbggenerated
     */
    public void setCatPromotePosId(Long catPromotePosId) {
        this.catPromotePosId = catPromotePosId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cat_promoted_commodity.commodity_code
     *
     * @return the value of cat_promoted_commodity.commodity_code
     *
     * @mbggenerated
     */
    public String getCommodityCode() {
        return commodityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cat_promoted_commodity.commodity_code
     *
     * @param commodityCode the value for cat_promoted_commodity.commodity_code
     *
     * @mbggenerated
     */
    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode == null ? null : commodityCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cat_promoted_commodity.product_code
     *
     * @return the value of cat_promoted_commodity.product_code
     *
     * @mbggenerated
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cat_promoted_commodity.product_code
     *
     * @param productCode the value for cat_promoted_commodity.product_code
     *
     * @mbggenerated
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cat_promoted_commodity.commodity_name
     *
     * @return the value of cat_promoted_commodity.commodity_name
     *
     * @mbggenerated
     */
    public String getCommodityName() {
        return commodityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cat_promoted_commodity.commodity_name
     *
     * @param commodityName the value for cat_promoted_commodity.commodity_name
     *
     * @mbggenerated
     */
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cat_promoted_commodity.commodity_spec
     *
     * @return the value of cat_promoted_commodity.commodity_spec
     *
     * @mbggenerated
     */
    public String getCommoditySpec() {
        return commoditySpec;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cat_promoted_commodity.commodity_spec
     *
     * @param commoditySpec the value for cat_promoted_commodity.commodity_spec
     *
     * @mbggenerated
     */
    public void setCommoditySpec(String commoditySpec) {
        this.commoditySpec = commoditySpec == null ? null : commoditySpec.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cat_promoted_commodity.commodity_img_url
     *
     * @return the value of cat_promoted_commodity.commodity_img_url
     *
     * @mbggenerated
     */
    public String getCommodityImgUrl() {
        return commodityImgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cat_promoted_commodity.commodity_img_url
     *
     * @param commodityImgUrl the value for cat_promoted_commodity.commodity_img_url
     *
     * @mbggenerated
     */
    public void setCommodityImgUrl(String commodityImgUrl) {
        this.commodityImgUrl = commodityImgUrl == null ? null : commodityImgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cat_promoted_commodity.display_order
     *
     * @return the value of cat_promoted_commodity.display_order
     *
     * @mbggenerated
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cat_promoted_commodity.display_order
     *
     * @param displayOrder the value for cat_promoted_commodity.display_order
     *
     * @mbggenerated
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

	/**
	 * @return the catPromotePos
	 */
	public CatPromotePos getCatPromotePos() {
		return catPromotePos;
	}

	/**
	 * @param catPromotePos the catPromotePos to set
	 */
	public void setCatPromotePos(CatPromotePos catPromotePos) {
		this.catPromotePos = catPromotePos;
	}
}