package com.spfood.pms.intf.domain;

import com.spfood.kernel.domain.DomainObject;

public class ProductProperty implements DomainObject {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_property.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_property.product_code
     *
     * @mbggenerated
     */
    private String productCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_property.product_property_name
     *
     * @mbggenerated
     */
    private String productPropertyName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_property.product_property_value
     *
     * @mbggenerated
     */
    private String productPropertyValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_property.customer_define_flag
     *
     * @mbggenerated
     */
    private Integer customerDefineFlag;

    
    public ProductProperty() {
		super();
	}

	public ProductProperty(String productCode,
			String productPropertyName, String productPropertyValue) {
		super();
		this.productCode = productCode;
		this.productPropertyName = productPropertyName;
		this.productPropertyValue = productPropertyValue;
	}
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product_property.id
     *
     * @return the value of pms_product_property.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product_property.id
     *
     * @param id the value for pms_product_property.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product_property.product_code
     *
     * @return the value of pms_product_property.product_code
     *
     * @mbggenerated
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product_property.product_code
     *
     * @param productCode the value for pms_product_property.product_code
     *
     * @mbggenerated
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product_property.product_property_name
     *
     * @return the value of pms_product_property.product_property_name
     *
     * @mbggenerated
     */
    public String getProductPropertyName() {
        return productPropertyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product_property.product_property_name
     *
     * @param productPropertyName the value for pms_product_property.product_property_name
     *
     * @mbggenerated
     */
    public void setProductPropertyName(String productPropertyName) {
        this.productPropertyName = productPropertyName == null ? null : productPropertyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product_property.product_property_value
     *
     * @return the value of pms_product_property.product_property_value
     *
     * @mbggenerated
     */
    public String getProductPropertyValue() {
        return productPropertyValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product_property.product_property_value
     *
     * @param productPropertyValue the value for pms_product_property.product_property_value
     *
     * @mbggenerated
     */
    public void setProductPropertyValue(String productPropertyValue) {
        this.productPropertyValue = productPropertyValue == null ? null : productPropertyValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product_property.customer_define_flag
     *
     * @return the value of pms_product_property.customer_define_flag
     *
     * @mbggenerated
     */
    public Integer getCustomerDefineFlag() {
        return customerDefineFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product_property.customer_define_flag
     *
     * @param customerDefineFlag the value for pms_product_property.customer_define_flag
     *
     * @mbggenerated
     */
    public void setCustomerDefineFlag(Integer customerDefineFlag) {
        this.customerDefineFlag = customerDefineFlag;
    }

	@Override
	public String toString() {
		return "ProductProperty [id=" + id + ", productCode=" + productCode
				+ ", productPropertyName=" + productPropertyName
				+ ", productPropertyValue=" + productPropertyValue
				+ ", customerDefineFlag=" + customerDefineFlag + "]";
	}
}