package com.spfood.pms.search.intf.domain;

import com.spfood.kernel.domain.DomainObject;

/**
 * 产品品类属性值表
 * @author fengjunchao
 *2016-12-06
 *
 */
public class ProductCategoryPropertyValue implements DomainObject {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_category_property_value.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_category_property_value.category_property_code
     *
     * @mbggenerated
     */
    private ProductCategoryProperty productCategoryProperty;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_category_property_value.category_property_value
     *
     * @mbggenerated
     */
    private String categoryPropertyValue;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product_category_property_value.id
     *
     * @return the value of pms_product_category_property_value.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product_category_property_value.id
     *
     * @param id the value for pms_product_category_property_value.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product_category_property_value.category_property_value
     *
     * @return the value of pms_product_category_property_value.category_property_value
     *
     * @mbggenerated
     */
    public String getCategoryPropertyValue() {
        return categoryPropertyValue;
    }

    public ProductCategoryProperty getProductCategoryProperty() {
		return productCategoryProperty;
	}

	public void setProductCategoryProperty(
			ProductCategoryProperty productCategoryProperty) {
		this.productCategoryProperty = productCategoryProperty;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product_category_property_value.category_property_value
     *
     * @param categoryPropertyValue the value for pms_product_category_property_value.category_property_value
     *
     * @mbggenerated
     */
    public void setCategoryPropertyValue(String categoryPropertyValue) {
        this.categoryPropertyValue = categoryPropertyValue == null ? null : categoryPropertyValue.trim();
    }
}