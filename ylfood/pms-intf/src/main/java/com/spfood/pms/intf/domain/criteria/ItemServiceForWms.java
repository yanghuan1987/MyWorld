/**
 * 
 */
package com.spfood.pms.intf.domain.criteria;

import com.spfood.kernel.domain.DomainObject;

/**
 * 物品信息列表服务
 * 功能：同步物品新数据到WMS系统
 * @author yanghuan
 *
 */
public class ItemServiceForWms implements DomainObject {
	private static final long serialVersionUID = 1L;
	/*物品编码*/
	private String itemCode;
	
	/*物品名称*/
	private String itemName;
	
	/*物品类别编码*/
	private String itemCategoryCode;
	
	/*物品类别名称*/
	private String itemCategoryName;
	
	/*物品规格*/
	private String itemSpecificationValue;
	
	/*计量单位*/
	private String itemSpecificationUnitFirst;
	
	/*包装单位*/
	private String itemSpecificationUnitSecond;
	
	/*产品基本单位*/
	private String itemBuyUnitName;
	
	/*计量单位Value*/
	private String itemSpecificationUnitFirstValue;
	
	/*包装单位Value*/
	private String itemSpecificationUnitSecondValue;
	
	/*产品基本单位Value*/
	private String itemBuyUnitValue;
	
	/*数据来源*/
	private int dataSource;
	
	/*物品类型*/
	private int itemType;
	
    /**
     * 温区名称
     */
    private String itemTemperatureZoneName;
    
    /**
     * 温区编码
     */
    private String itemTemperatureZoneCode;
	
    /**
     * 属性名称
     */
    private String itemPropertyName;
    
    /**
     * 属性值
     */
    private String itemPropertyValue;
	
    /**
     * 税率名称
     */
    private String itemTaxRateName;
    
    /**
     * 税率编码
     */
    private String itemTaxRateValue;
    //状态
    private int itemStatus;
	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the itemCategoryCode
	 */
	public String getItemCategoryCode() {
		return itemCategoryCode;
	}

	/**
	 * @param itemCategoryCode the itemCategoryCode to set
	 */
	public void setItemCategoryCode(String itemCategoryCode) {
		this.itemCategoryCode = itemCategoryCode;
	}

	/**
	 * @return the itmeCategoryName
	 */
	public String getItmeCategoryName() {
		return itemCategoryName;
	}

	/**
	 * @param itmeCategoryName the itmeCategoryName to set
	 */
	public void setItmeCategoryName(String itmeCategoryName) {
		this.itemCategoryName = itmeCategoryName;
	}

	/**
	 * @return the itemSpecificationValue
	 */
	public String getItemSpecificationValue() {
		return itemSpecificationValue;
	}

	/**
	 * @param itemSpecificationValue the itemSpecificationValue to set
	 */
	public void setItemSpecificationValue(String itemSpecificationValue) {
		this.itemSpecificationValue = itemSpecificationValue;
	}

	/**
	 * @return the itemSpecificationUnitFirst
	 */
	public String getItemSpecificationUnitFirst() {
		return itemSpecificationUnitFirst;
	}

	/**
	 * @param itemSpecificationUnitFirst the itemSpecificationUnitFirst to set
	 */
	public void setItemSpecificationUnitFirst(String itemSpecificationUnitFirst) {
		this.itemSpecificationUnitFirst = itemSpecificationUnitFirst;
	}

	/**
	 * @return the itemSpecificationUnitSecond
	 */
	public String getItemSpecificationUnitSecond() {
		return itemSpecificationUnitSecond;
	}

	/**
	 * @param itemSpecificationUnitSecond the itemSpecificationUnitSecond to set
	 */
	public void setItemSpecificationUnitSecond(String itemSpecificationUnitSecond) {
		this.itemSpecificationUnitSecond = itemSpecificationUnitSecond;
	}

	/**
	 * @return the dataSource
	 */
	public int getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(int dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return the itemType
	 */
	public int getItemType() {
		return itemType;
	}

	/**
	 * @param itemType the itemType to set
	 */
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	/**
	 * @return the itemBuyUnitName
	 */
	public String getItemBuyUnitName() {
		return itemBuyUnitName;
	}

	/**
	 * @param itemBuyUnitName the itemBuyUnitName to set
	 */
	public void setItemBuyUnitName(String itemBuyUnitName) {
		this.itemBuyUnitName = itemBuyUnitName;
	}

	/**
	 * @return the itemSpecificationUnitFirstValue
	 */
	public String getItemSpecificationUnitFirstValue() {
		return itemSpecificationUnitFirstValue;
	}

	/**
	 * @param itemSpecificationUnitFirstValue the itemSpecificationUnitFirstValue to set
	 */
	public void setItemSpecificationUnitFirstValue(
			String itemSpecificationUnitFirstValue) {
		this.itemSpecificationUnitFirstValue = itemSpecificationUnitFirstValue;
	}

	/**
	 * @return the itemSpecificationUnitSecondValue
	 */
	public String getItemSpecificationUnitSecondValue() {
		return itemSpecificationUnitSecondValue;
	}

	/**
	 * @param itemSpecificationUnitSecondValue the itemSpecificationUnitSecondValue to set
	 */
	public void setItemSpecificationUnitSecondValue(
			String itemSpecificationUnitSecondValue) {
		this.itemSpecificationUnitSecondValue = itemSpecificationUnitSecondValue;
	}

	/**
	 * @return the itemBuyUnitValue
	 */
	public String getItemBuyUnitValue() {
		return itemBuyUnitValue;
	}

	/**
	 * @param itemBuyUnitValue the itemBuyUnitValue to set
	 */
	public void setItemBuyUnitValue(String itemBuyUnitValue) {
		this.itemBuyUnitValue = itemBuyUnitValue;
	}

	/**
	 * @return the itemTemperatureZoneName
	 */
	public String getItemTemperatureZoneName() {
		return itemTemperatureZoneName;
	}

	/**
	 * @param itemTemperatureZoneName the itemTemperatureZoneName to set
	 */
	public void setItemTemperatureZoneName(String itemTemperatureZoneName) {
		this.itemTemperatureZoneName = itemTemperatureZoneName;
	}

	/**
	 * @return the itemTemperatureZoneCode
	 */
	public String getItemTemperatureZoneCode() {
		return itemTemperatureZoneCode;
	}

	/**
	 * @param itemTemperatureZoneCode the itemTemperatureZoneCode to set
	 */
	public void setItemTemperatureZoneCode(String itemTemperatureZoneCode) {
		this.itemTemperatureZoneCode = itemTemperatureZoneCode;
	}

	/**
	 * @return the itemPropertyName
	 */
	public String getItemPropertyName() {
		return itemPropertyName;
	}

	/**
	 * @param itemPropertyName the itemPropertyName to set
	 */
	public void setItemPropertyName(String itemPropertyName) {
		this.itemPropertyName = itemPropertyName;
	}

	/**
	 * @return the itemPropertyValue
	 */
	public String getItemPropertyValue() {
		return itemPropertyValue;
	}

	/**
	 * @param itemPropertyValue the itemPropertyValue to set
	 */
	public void setItemPropertyValue(String itemPropertyValue) {
		this.itemPropertyValue = itemPropertyValue;
	}

	/**
	 * @return the itemStatus
	 */
	public int getItemStatus() {
		return itemStatus;
	}

	/**
	 * @param itemStatus the itemStatus to set
	 */
	public void setItemStatus(int itemStatus) {
		this.itemStatus = itemStatus;
	}

	/**
	 * @return the itemTaxRateName
	 */
	public String getItemTaxRateName() {
		return itemTaxRateName;
	}

	/**
	 * @param itemTaxRateName the itemTaxRateName to set
	 */
	public void setItemTaxRateName(String itemTaxRateName) {
		this.itemTaxRateName = itemTaxRateName;
	}

	/**
	 * @return the itemTaxRateValue
	 */
	public String getItemTaxRateValue() {
		return itemTaxRateValue;
	}

	/**
	 * @param itemTaxRateValue the itemTaxRateValue to set
	 */
	public void setItemTaxRateValue(String itemTaxRateValue) {
		this.itemTaxRateValue = itemTaxRateValue;
	}

}
