package com.spfood.pms.intf.domain;

import com.spfood.kernel.domain.DomainObject;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Product implements DomainObject {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.product_code
     *
     * @mbggenerated
     */
    private String productCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.category_code
     *
     * @mbggenerated
     */
    private String categoryCode;
    
    /**
     * 品类对象
     */
    private ProductCategory category;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.product_gs1_code
     *
     * @mbggenerated
     */
    private String productGs1Code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.product_name
     *
     * @mbggenerated
     */
    private String productName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.product_show_name
     *
     * @mbggenerated
     */
    private String productShowName;
    
    /**
     * 产品采购单位编码
     */
    private String productBuyUnitCode;
    
    /**
     * 产品采购单位名称
     */
    private String productBuyUnitName;
    
    /**
     * 温区名称
     */
    private String productTemperatureZoneName;
    
    /**
     * 温区编码
     */
    private String productTemperatureZoneCode;
    
    /**
     * 税率名称
     */
    private String taxRateName;
    
    /**
     * 税率值
     */
    private String taxRateValue;
    
    /**
     * 保质期
     */
    private String shelfLife;
    
    /**
     * 保质期单位名称
     */
    private String shelfLifeName;

    
    /**
     * 保质期单位值
     */
    private String shelfLifeValue;
    
    /**
     * 货架期
     */
    private String saleDate;

    
    /**
     * 货架期单位名称
     */
    private String saleDateName;
    
    /**
     * 货架期单位值
     */
    private String saleDateValue;


    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.product_terminal
     *
     * @mbggenerated
     */
    private BigDecimal productTerminal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.product_status
     *
     * @mbggenerated
     */
    private Integer productStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.sale_flag
     *
     * @mbggenerated
     */
    private Integer saleFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.composite_flag
     *
     * @mbggenerated
     */
    private Integer compositeFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.product_specification_value
     *
     * @mbggenerated
     */
    private Float productSpecificationValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.product_specification_unit_first
     *
     * @mbggenerated
     */
    private String productSpecificationUnitFirst;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.product_specification_unit_second
     *
     * @mbggenerated
     */
    private String productSpecificationUnitSecond;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.last_update_date
     *
     * @mbggenerated
     */
    private Date lastUpdateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.last_update_user
     *
     * @mbggenerated
     */
    private String lastUpdateUser;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.last_update_user
     *
     * @mbggenerated
     */
    private String productSpecificationUnitFirstValue;
    
    /**
	 * @return the productSpecificationUnitFirstValue
	 */
	public String getProductSpecificationUnitFirstValue() {
		return productSpecificationUnitFirstValue;
	}

	/**
	 * @param productSpecificationUnitFirstValue the productSpecificationUnitFirstValue to set
	 */
	public void setProductSpecificationUnitFirstValue(
			String productSpecificationUnitFirstValue) {
		this.productSpecificationUnitFirstValue = productSpecificationUnitFirstValue;
	}

	/**
	 * @return the productSpecificationUnitSecondValue
	 */
	public String getProductSpecificationUnitSecondValue() {
		return productSpecificationUnitSecondValue;
	}

	/**
	 * @param productSpecificationUnitSecondValue the productSpecificationUnitSecondValue to set
	 */
	public void setProductSpecificationUnitSecondValue(
			String productSpecificationUnitSecondValue) {
		this.productSpecificationUnitSecondValue = productSpecificationUnitSecondValue;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product.last_update_user
     *
     * @mbggenerated
     */
    private String productSpecificationUnitSecondValue;
    
    //产品图片属性
    private List<ProductPicture> productPictures;
    
    //产品属性
    private List<ProductProperty> productProperties;

    //产品返回属性在界面显示
    private String[] productPropertyArray;

    //产品属性搜索
    private ProductProperty propertySearchCriteria;
    
    //包装单位属性
    private List<ProductPackUnit> productPackUnit;
    
    //辅料规格
    private String productSecondarySpecification;

    /**
	 * @return the productTemperatureZoneCode
	 */
	public String getProductTemperatureZoneCode() {
		return productTemperatureZoneCode;
	}

	/**
	 * @param productTemperatureZoneCode the productTemperatureZoneCode to set
	 */
	public void setProductTemperatureZoneCode(String productTemperatureZoneCode) {
		this.productTemperatureZoneCode = productTemperatureZoneCode;
	}

	/**
	 * @return the productTemperatureZoneName
	 */
	public String getProductTemperatureZoneName() {
		return productTemperatureZoneName;
	}

	/**
	 * @param productTemperatureZoneName the productTemperatureZoneName to set
	 */
	public void setProductTemperatureZoneName(String productTemperatureZoneName) {
		this.productTemperatureZoneName = productTemperatureZoneName;
	}

	/**
	 * @return the productPackUnit
	 */
	public List<ProductPackUnit> getProductPackUnit() {
		return productPackUnit;
	}

	/**
	 * @param productPackUnit the productPackUnit to set
	 */
	public void setProductPackUnit(List<ProductPackUnit> productPackUnit) {
		this.productPackUnit = productPackUnit;
	}

	public ProductProperty getPropertySearchCriteria() {
		return propertySearchCriteria;
	}

	public void setPropertySearchCriteria(ProductProperty propertySearchCriteria) {
		this.propertySearchCriteria = propertySearchCriteria;
	}

	public List<ProductPicture> getProductPictures() {
		return productPictures;
	}

	public void setProductPictures(List<ProductPicture> productPictures) {
		this.productPictures = productPictures;
	}

	public String getProductBuyUnitCode() {
		return productBuyUnitCode;
	}

	public void setProductBuyUnitCode(String productBuyUnitCode) {
		this.productBuyUnitCode = productBuyUnitCode;
	}

	public String getProductBuyUnitName() {
		return productBuyUnitName;
	}

	public void setProductBuyUnitName(String productBuyUnitName) {
		this.productBuyUnitName = productBuyUnitName;
	}

	public List<ProductProperty> getProductProperties() {
		return productProperties;
	}

	public void setProductProperties(List<ProductProperty> productProperties) {
		this.productProperties = productProperties;
	}

	public String[] getProductPropertyArray() {
		return productPropertyArray;
	}

	public void setProductPropertyArray(String[] productPropertyArray) {
		this.productPropertyArray = productPropertyArray;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product.id
     *
     * @return the value of pms_product.id
     *
     * @mbggenerated
     */  
    
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product.id
     *
     * @param id the value for pms_product.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product.product_code
     *
     * @return the value of pms_product.product_code
     *
     * @mbggenerated
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product.product_code
     *
     * @param productCode the value for pms_product.product_code
     *
     * @mbggenerated
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product.category_code
     *
     * @return the value of pms_product.category_code
     *
     * @mbggenerated
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product.category_code
     *
     * @param categoryCode the value for pms_product.category_code
     *
     * @mbggenerated
     */
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product.product_gs1_code
     *
     * @return the value of pms_product.product_gs1_code
     *
     * @mbggenerated
     */
    public String getProductGs1Code() {
        return productGs1Code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product.product_gs1_code
     *
     * @param productGs1Code the value for pms_product.product_gs1_code
     *
     * @mbggenerated
     */
    public void setProductGs1Code(String productGs1Code) {
        this.productGs1Code = productGs1Code == null ? null : productGs1Code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product.product_name
     *
     * @return the value of pms_product.product_name
     *
     * @mbggenerated
     */
    public String getProductName() {
        return productName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product.product_name
     *
     * @param productName the value for pms_product.product_name
     *
     * @mbggenerated
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product.product_show_name
     *
     * @return the value of pms_product.product_show_name
     *
     * @mbggenerated
     */
    public String getProductShowName() {
        return productShowName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product.product_show_name
     *
     * @param productShowName the value for pms_product.product_show_name
     *
     * @mbggenerated
     */
    public void setProductShowName(String productShowName) {
        this.productShowName = productShowName == null ? null : productShowName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product.product_terminal
     *
     * @return the value of pms_product.product_terminal
     *
     * @mbggenerated
     */
    public BigDecimal getProductTerminal() {
        return productTerminal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product.product_terminal
     *
     * @param productTerminal the value for pms_product.product_terminal
     *
     * @mbggenerated
     */
    public void setProductTerminal(BigDecimal productTerminal) {
        this.productTerminal = productTerminal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product.product_status
     *
     * @return the value of pms_product.product_status
     *
     * @mbggenerated
     */
    public Integer getProductStatus() {
        return productStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product.product_status
     *
     * @param productStatus the value for pms_product.product_status
     *
     * @mbggenerated
     */
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product.sale_flag
     *
     * @return the value of pms_product.sale_flag
     *
     * @mbggenerated
     */
    public Integer getSaleFlag() {
        return saleFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product.sale_flag
     *
     * @param saleFlag the value for pms_product.sale_flag
     *
     * @mbggenerated
     */
    public void setSaleFlag(Integer saleFlag) {
        this.saleFlag = saleFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product.composite_flag
     *
     * @return the value of pms_product.composite_flag
     *
     * @mbggenerated
     */
    public Integer getCompositeFlag() {
        return compositeFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product.composite_flag
     *
     * @param compositeFlag the value for pms_product.composite_flag
     *
     * @mbggenerated
     */
    public void setCompositeFlag(Integer compositeFlag) {
        this.compositeFlag = compositeFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product.create_date
     *
     * @return the value of pms_product.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product.create_date
     *
     * @param createDate the value for pms_product.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product.create_user
     *
     * @return the value of pms_product.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product.create_user
     *
     * @param createUser the value for pms_product.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product.last_update_date
     *
     * @return the value of pms_product.last_update_date
     *
     * @mbggenerated
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product.last_update_date
     *
     * @param lastUpdateDate the value for pms_product.last_update_date
     *
     * @mbggenerated
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product.last_update_user
     *
     * @return the value of pms_product.last_update_user
     *
     * @mbggenerated
     */
    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product.last_update_user
     *
     * @param lastUpdateUser the value for pms_product.last_update_user
     *
     * @mbggenerated
     */
    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }

	public Float getProductSpecificationValue() {
		return productSpecificationValue;
	}

	public void setProductSpecificationValue(Float productSpecificationValue) {
		this.productSpecificationValue = productSpecificationValue;
	}

	public String getProductSpecificationUnitFirst() {
		return productSpecificationUnitFirst;
	}

	public void setProductSpecificationUnitFirst(
			String productSpecificationUnitFirst) {
		this.productSpecificationUnitFirst = productSpecificationUnitFirst;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public String getProductSpecificationUnitSecond() {
		return productSpecificationUnitSecond;
	}

	public void setProductSpecificationUnitSecond(
			String productSpecificationUnitSecond) {
		this.productSpecificationUnitSecond = productSpecificationUnitSecond;
	}

	public Product() {
	
    }

	public Product(String productCode, String categoryCode,
			String productName) {
		super();
		this.productCode = productCode;
		this.categoryCode = categoryCode;
		this.productName = productName;
	}

	public Product(String productCode, String categoryCode, String productName,
			Integer compositeFlag) {
		super();
		this.productCode = productCode;
		this.categoryCode = categoryCode;
		this.productName = productName;
		this.compositeFlag = compositeFlag;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productCode=" + productCode
				+ ", categoryCode=" + categoryCode + ", productGs1Code="
				+ productGs1Code + ", productName=" + productName
				+ ", productShowName=" + productShowName + ", productTerminal="
				+ productTerminal + ", productStatus=" + productStatus
				+ ", saleFlag=" + saleFlag + ", compositeFlag=" + compositeFlag
				+ ", productSpecificationValue=" + productSpecificationValue
				+ ", productSpecificationUnitFirst="
				+ productSpecificationUnitFirst
				+ ", productSpecificationUnitSecond="
				+ productSpecificationUnitSecond + ", createDate=" + createDate
				+ ", createUser=" + createUser + ", lastUpdateDate="
				+ lastUpdateDate + ", lastUpdateUser=" + lastUpdateUser
				+ ", productPictures=" + productPictures
				+ ", productProperties=" + productProperties
				+ ", productPropertyArray="
				+ Arrays.toString(productPropertyArray)
				+ ", propertySearchCriteria=" + propertySearchCriteria
				+ ", getPropertySearchCriteria()="
				+ getPropertySearchCriteria() + ", getProductPictures()="
				+ getProductPictures() + ", getProductProperties()="
				+ getProductProperties() + ", getProductPropertyArray()="
				+ Arrays.toString(getProductPropertyArray()) + ", getId()="
				+ getId() + ", getProductCode()=" + getProductCode()
				+ ", getCategoryCode()=" + getCategoryCode()
				+ ", getProductGs1Code()=" + getProductGs1Code()
				+ ", getProductName()=" + getProductName()
				+ ", getProductShowName()=" + getProductShowName()
				+ ", getProductTerminal()=" + getProductTerminal()
				+ ", getProductStatus()=" + getProductStatus()
				+ ", getSaleFlag()=" + getSaleFlag() + ", getCompositeFlag()="
				+ getCompositeFlag() + ", getCreateDate()=" + getCreateDate()
				+ ", getCreateUser()=" + getCreateUser()
				+ ", getLastUpdateDate()=" + getLastUpdateDate()
				+ ", getLastUpdateUser()=" + getLastUpdateUser()
				+ ", getProductSpecificationValue()="
				+ getProductSpecificationValue()
				+ ", getProductSpecificationUnitFirst()="
				+ getProductSpecificationUnitFirst()
				+ ", getProductSpecificationUnitSecond()="
				+ getProductSpecificationUnitSecond() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	/**
	 * @return the taxRateName
	 */
	public String getTaxRateName() {
		return taxRateName;
	}

	/**
	 * @param taxRateName the taxRateName to set
	 */
	public void setTaxRateName(String taxRateName) {
		this.taxRateName = taxRateName;
	}

	/**
	 * @return the taxRateValue
	 */
	public String getTaxRateValue() {
		return taxRateValue;
	}

	/**
	 * @param taxRateValue the taxRateValue to set
	 */
	public void setTaxRateValue(String taxRateValue) {
		this.taxRateValue = taxRateValue;
	}

	/**
	 * @return the shelfLife
	 */
	public String getShelfLife() {
		return shelfLife;
	}

	/**
	 * @param shelfLife the shelfLife to set
	 */
	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}

	/**
	 * @return the shelfLifeName
	 */
	public String getShelfLifeName() {
		return shelfLifeName;
	}

	/**
	 * @param shelfLifeName the shelfLifeName to set
	 */
	public void setShelfLifeName(String shelfLifeName) {
		this.shelfLifeName = shelfLifeName;
	}

	/**
	 * @return the shelfLifeValue
	 */
	public String getShelfLifeValue() {
		return shelfLifeValue;
	}

	/**
	 * @param shelfLifeValue the shelfLifeValue to set
	 */
	public void setShelfLifeValue(String shelfLifeValue) {
		this.shelfLifeValue = shelfLifeValue;
	}

	/**
	 * @return the saleDate
	 */
	public String getSaleDate() {
		return saleDate;
	}

	/**
	 * @param saleDate the saleDate to set
	 */
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	/**
	 * @return the saleDateName
	 */
	public String getSaleDateName() {
		return saleDateName;
	}

	/**
	 * @param saleDateName the saleDateName to set
	 */
	public void setSaleDateName(String saleDateName) {
		this.saleDateName = saleDateName;
	}

	/**
	 * @return the saleDateValue
	 */
	public String getSaleDateValue() {
		return saleDateValue;
	}

	/**
	 * @param saleDateValue the saleDateValue to set
	 */
	public void setSaleDateValue(String saleDateValue) {
		this.saleDateValue = saleDateValue;
	}

	/**
	 * @return the productSecondarySpecification
	 */
	public String getProductSecondarySpecification() {
		return productSecondarySpecification;
	}

	/**
	 * @param productSecondarySpecification the productSecondarySpecification to set
	 */
	public void setProductSecondarySpecification(
			String productSecondarySpecification) {
		this.productSecondarySpecification = productSecondarySpecification;
	}
}