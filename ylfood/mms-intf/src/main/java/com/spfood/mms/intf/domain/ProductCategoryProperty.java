package com.spfood.mms.intf.domain;

import com.spfood.kernel.domain.DomainObject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 产品品类属性表
 * @author fengjunchao
 *2016-12-06
 */
public class ProductCategoryProperty implements DomainObject {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mms_product_category_property.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mms_product_category_property.category_code
     *
     * @mbggenerated
     */
    private ProductCategory productCategory;
    
    /**
	 * 属性值集合
	 */
	private List<ProductCategoryPropertyValue> productCategoryPropertyValues;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mms_product_category_property.category_property_name
     *
     * @mbggenerated
     */
    private String categoryPropertyName;
    
    /**
     * 是否筛选
     */
    private Integer selectFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mms_product_category_property.show_order
     *
     * @mbggenerated
     */
    private BigDecimal showOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mms_product_category_property.category_property_description
     *
     * @mbggenerated
     */
    private String categoryPropertyDescription;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mms_product_category_property.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mms_product_category_property.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mms_product_category_property.last_update_date
     *
     * @mbggenerated
     */
    private Date lastUpdateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mms_product_category_property.last_update_user
     *
     * @mbggenerated
     */
    private String lastUpdateUser;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mms_product_category_property.id
     *
     * @return the value of mms_product_category_property.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mms_product_category_property.id
     *
     * @param id the value for mms_product_category_property.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mms_product_category_property.category_property_name
     *
     * @return the value of mms_product_category_property.category_property_name
     *
     * @mbggenerated
     */
    public String getCategoryPropertyName() {
        return categoryPropertyName;
    }

    public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<ProductCategoryPropertyValue> getProductCategoryPropertyValues() {
		return productCategoryPropertyValues;
	}

	public void setProductCategoryPropertyValues(
			List<ProductCategoryPropertyValue> productCategoryPropertyValues) {
		this.productCategoryPropertyValues = productCategoryPropertyValues;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mms_product_category_property.category_property_name
     *
     * @param categoryPropertyName the value for mms_product_category_property.category_property_name
     *
     * @mbggenerated
     */
    public void setCategoryPropertyName(String categoryPropertyName) {
        this.categoryPropertyName = categoryPropertyName == null ? null : categoryPropertyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mms_product_category_property.show_order
     *
     * @return the value of mms_product_category_property.show_order
     *
     * @mbggenerated
     */
    public BigDecimal getShowOrder() {
        return showOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mms_product_category_property.show_order
     *
     * @param showOrder the value for mms_product_category_property.show_order
     *
     * @mbggenerated
     */
    public void setShowOrder(BigDecimal showOrder) {
        this.showOrder = showOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mms_product_category_property.category_property_description
     *
     * @return the value of mms_product_category_property.category_property_description
     *
     * @mbggenerated
     */
    public String getCategoryPropertyDescription() {
        return categoryPropertyDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mms_product_category_property.category_property_description
     *
     * @param categoryPropertyDescription the value for mms_product_category_property.category_property_description
     *
     * @mbggenerated
     */
    public void setCategoryPropertyDescription(String categoryPropertyDescription) {
        this.categoryPropertyDescription = categoryPropertyDescription == null ? null : categoryPropertyDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mms_product_category_property.create_date
     *
     * @return the value of mms_product_category_property.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mms_product_category_property.create_date
     *
     * @param createDate the value for mms_product_category_property.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mms_product_category_property.create_user
     *
     * @return the value of mms_product_category_property.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mms_product_category_property.create_user
     *
     * @param createUser the value for mms_product_category_property.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mms_product_category_property.last_update_date
     *
     * @return the value of mms_product_category_property.last_update_date
     *
     * @mbggenerated
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mms_product_category_property.last_update_date
     *
     * @param lastUpdateDate the value for mms_product_category_property.last_update_date
     *
     * @mbggenerated
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(Integer selectFlag) {
		this.selectFlag = selectFlag;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mms_product_category_property.last_update_user
     *
     * @return the value of mms_product_category_property.last_update_user
     *
     * @mbggenerated
     */
    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mms_product_category_property.last_update_user
     *
     * @param lastUpdateUser the value for mms_product_category_property.last_update_user
     *
     * @mbggenerated
     */
    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }
}