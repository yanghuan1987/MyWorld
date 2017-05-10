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
	private String itmeCategoryName;
	
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
		return itmeCategoryName;
	}

	/**
	 * @param itmeCategoryName the itmeCategoryName to set
	 */
	public void setItmeCategoryName(String itmeCategoryName) {
		this.itmeCategoryName = itmeCategoryName;
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
	 * @return the productTemperatureZoneName
	 */
	public String getProductTemperatureZoneName() {
		return itemTemperatureZoneName;
	}

	/**
	 * @param productTemperatureZoneName the productTemperatureZoneName to set
	 */
	public void setProductTemperatureZoneName(String productTemperatureZoneName) {
		this.itemTemperatureZoneName = productTemperatureZoneName;
	}

	/**
	 * @return the productTemperatureZoneCode
	 */
	public String getProductTemperatureZoneCode() {
		return itemTemperatureZoneCode;
	}

	/**
	 * @param productTemperatureZoneCode the productTemperatureZoneCode to set
	 */
	public void setProductTemperatureZoneCode(String productTemperatureZoneCode) {
		this.itemTemperatureZoneCode = productTemperatureZoneCode;
	}
	

}
