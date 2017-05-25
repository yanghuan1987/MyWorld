package com.spfood.pms.intf.domain;

import com.spfood.kernel.domain.DomainObject;

/*
 * This class is used for encapsulation search criteria for product:
 * that include three parts:
 * 1. page information: page number, page size
 * 2. product code
 * 3. product property
 * 
 * */
public class ProductSearchCriteria implements DomainObject {

	private static final long serialVersionUID = 1L;
	//页数
	private int pageNum;
	//每页的数量
	private int pageSize;
	//品类编码
	private String categoryCode;
	//产品编码
	private String productCode;
	//是否可售
	private Integer saleFlag;
	//是否组合产品
	private Integer compositeFlag;
	//数据量较少，目前只支持单属性搜索
	private ProductProperty productProperty;
	//品类名称
	private String categoryName;
	//产品名称
	private String productName;
	//是否有采购单位
	private Boolean isBuyUnit;
	//gs1编码
    private String productGs1Code;
    /**
     * 温区编码
     */
    private String productTemperatureZoneCode;

	//产品状态
    private Integer productStatus;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public ProductProperty getProductProperty() {
		return productProperty;
	}

	public void setProductProperty(ProductProperty productProperty) {
		this.productProperty = productProperty;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Integer getSaleFlag() {
		return saleFlag;
	}

	public void setSaleFlag(Integer saleFlag) {
		this.saleFlag = saleFlag;
	}

	public Integer getCompositeFlag() {
		return compositeFlag;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Boolean getIsBuyUnit() {
		return isBuyUnit;
	}

	public void setIsBuyUnit(Boolean isBuyUnit) {
		this.isBuyUnit = isBuyUnit;
	}

	public void setCompositeFlag(Integer compositeFlag) {
		this.compositeFlag = compositeFlag;
	}

	@Override
	public String toString() {
		return "ProductSearchCriteria [pageNum=" + pageNum + ", pageSize="
				+ pageSize + ", categoryCode=" + categoryCode
				+ ", productCode=" + productCode + ", saleFlag=" + saleFlag
				+ ", compositeFlag=" + compositeFlag + ", productProperty="
				+ productProperty + "]";
	}

	/**
	 * @return the productGs1Code
	 */
	public String getProductGs1Code() {
		return productGs1Code;
	}

	/**
	 * @param productGs1Code the productGs1Code to set
	 */
	public void setProductGs1Code(String productGs1Code) {
		this.productGs1Code = productGs1Code;
	}

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
	 * @return the productStatus
	 */
	public Integer getProductStatus() {
		return productStatus;
	}

	/**
	 * @param productStatus the productStatus to set
	 */
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
}
