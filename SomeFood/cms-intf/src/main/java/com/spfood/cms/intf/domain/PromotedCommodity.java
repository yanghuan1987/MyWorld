package com.spfood.cms.intf.domain;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


import com.spfood.kernel.domain.DomainObject;

public class PromotedCommodity implements DomainObject {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promoted_commodity.id
     *
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promoted_commodity.comm_promote_pos
     *
     */
    private String commPromotePos;


    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promoted_commodity.commodity_code
     *
     */
    @Size(min=1, max=16)
    private String commodityCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promoted_commodity.product_code
     *
     */
    @Size(min=1, max=16)
    private String productCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promoted_commodity.commodity_name
     *
     */
    @Size(max=128)
    private String commodityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promoted_commodity.commodity_spec
     *
     */
    @Size(max=16)
    private String commoditySpec;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promoted_commodity.image_url
     *
     */
    @Size(max=256)
    private String imageUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promoted_commodity.display_order
     *
     */
    @Min(1)
    @Max(99)
    private Integer displayOrder;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promoted_commodity.id
     *
     * @return the value of promoted_commodity.id
     *
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promoted_commodity.id
     *
     * @param id the value for promoted_commodity.id
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promoted_commodity.comm_promote_pos
     *
     * @return the value of promoted_commodity.comm_promote_pos
     *
     */
    public String getCommPromotePos() {
        return commPromotePos;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promoted_commodity.comm_promote_pos
     *
     * @param commPromotePos the value for promoted_commodity.comm_promote_pos
     *
     */
    public void setCommPromotePos(String commPromotePos) {
        this.commPromotePos = commPromotePos;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promoted_commodity.commodity_code
     *
     * @return the value of promoted_commodity.commodity_code
     *
     */
    public String getCommodityCode() {
        return commodityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promoted_commodity.commodity_code
     *
     * @param commodityCode the value for promoted_commodity.commodity_code
     *
     */
    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode == null ? null : commodityCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promoted_commodity.product_code
     *
     * @return the value of promoted_commodity.product_code
     *
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promoted_commodity.product_code
     *
     * @param productCode the value for promoted_commodity.product_code
     *
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promoted_commodity.commodity_name
     *
     * @return the value of promoted_commodity.commodity_name
     *
     */
    public String getCommodityName() {
        return commodityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promoted_commodity.commodity_name
     *
     * @param commodityName the value for promoted_commodity.commodity_name
     *
     */
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }


	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promoted_commodity.commodity_spec
     *
     * @return the value of promoted_commodity.commodity_spec
     *
     */
    public String getCommoditySpec() {
        return commoditySpec;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promoted_commodity.commodity_spec
     *
     * @param commoditySpec the value for promoted_commodity.commodity_spec
     *
     */
    public void setCommoditySpec(String commoditySpec) {
        this.commoditySpec = commoditySpec == null ? null : commoditySpec.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promoted_commodity.image_url
     *
     * @return the value of promoted_commodity.image_url
     *
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promoted_commodity.image_url
     *
     * @param imageUrl the value for promoted_commodity.image_url
     *
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promoted_commodity.display_order
     *
     * @return the value of promoted_commodity.display_order
     *
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promoted_commodity.display_order
     *
     * @param displayOrder the value for promoted_commodity.display_order
     *
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}