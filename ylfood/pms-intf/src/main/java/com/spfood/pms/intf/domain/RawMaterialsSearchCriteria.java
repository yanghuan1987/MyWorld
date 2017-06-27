package com.spfood.pms.intf.domain;

import com.spfood.kernel.domain.DomainObject;

/*
 * This class is used for encapsulation search criteria for product:
 * that include three parts:
 * 1. page information: page number, page size
 * 2. RawMaterials code
 * 3. RawMaterials property
 * 
 * */
public class RawMaterialsSearchCriteria implements DomainObject {

	private static final long serialVersionUID = 1L;
	//页数
	private int pageNum;
	//每页的数量
	private int pageSize;
	//品类编码
	private String categoryCode;
	//产品编码
	private String rawMaterialsCode;
	//是否可售
	private Integer saleFlag;
	//原料类型
	private Integer rawMaterialsType;
	//数据量较少，目前只支持单属性搜索
	private RawMaterialsProperty rawMaterialsProperty;
	//品类名称
	private String categoryName;
	//产品名称
	private String rawMaterialsName;
	//是否有采购单位
	private Boolean isBuyUnit;
	//gs1编码
    private String rawMaterialsGs1Code;
    /**
     * 温区编码
     */
    private String rawMaterialsTemperatureZoneCode;

	//产品状态
    private Integer rawMaterialsStatus;

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


	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public Boolean getIsBuyUnit() {
		return isBuyUnit;
	}

	public void setIsBuyUnit(Boolean isBuyUnit) {
		this.isBuyUnit = isBuyUnit;
	}

	/**
	 * @return the rawMaterialsCode
	 */
	public String getRawMaterialsCode() {
		return rawMaterialsCode;
	}

	/**
	 * @param rawMaterialsCode the rawMaterialsCode to set
	 */
	public void setRawMaterialsCode(String rawMaterialsCode) {
		this.rawMaterialsCode = rawMaterialsCode;
	}

	/**
	 * @return the rawMaterialsType
	 */
	public Integer getRawMaterialsType() {
		return rawMaterialsType;
	}

	/**
	 * @param rawMaterialsType the rawMaterialsType to set
	 */
	public void setRawMaterialsType(Integer rawMaterialsType) {
		this.rawMaterialsType = rawMaterialsType;
	}

	/**
	 * @return the rawMaterialsProperty
	 */
	public RawMaterialsProperty getRawMaterialsProperty() {
		return rawMaterialsProperty;
	}

	/**
	 * @param rawMaterialsProperty the rawMaterialsProperty to set
	 */
	public void setRawMaterialsProperty(RawMaterialsProperty rawMaterialsProperty) {
		this.rawMaterialsProperty = rawMaterialsProperty;
	}

	/**
	 * @return the rawMaterialsName
	 */
	public String getRawMaterialsName() {
		return rawMaterialsName;
	}

	/**
	 * @param rawMaterialsName the rawMaterialsName to set
	 */
	public void setRawMaterialsName(String rawMaterialsName) {
		this.rawMaterialsName = rawMaterialsName;
	}

	/**
	 * @return the rawMaterialsGs1Code
	 */
	public String getRawMaterialsGs1Code() {
		return rawMaterialsGs1Code;
	}

	/**
	 * @param rawMaterialsGs1Code the rawMaterialsGs1Code to set
	 */
	public void setRawMaterialsGs1Code(String rawMaterialsGs1Code) {
		this.rawMaterialsGs1Code = rawMaterialsGs1Code;
	}

	/**
	 * @return the rawMaterialsTemperatureZoneCode
	 */
	public String getRawMaterialsTemperatureZoneCode() {
		return rawMaterialsTemperatureZoneCode;
	}

	/**
	 * @param rawMaterialsTemperatureZoneCode the rawMaterialsTemperatureZoneCode to set
	 */
	public void setRawMaterialsTemperatureZoneCode(
			String rawMaterialsTemperatureZoneCode) {
		this.rawMaterialsTemperatureZoneCode = rawMaterialsTemperatureZoneCode;
	}

	/**
	 * @return the rawMaterialsStatus
	 */
	public Integer getRawMaterialsStatus() {
		return rawMaterialsStatus;
	}

	/**
	 * @param rawMaterialsStatus the rawMaterialsStatus to set
	 */
	public void setRawMaterialsStatus(Integer rawMaterialsStatus) {
		this.rawMaterialsStatus = rawMaterialsStatus;
	}
}
