package com.spfood.cms.utils;

/**
 * 商品状态枚举
 */
public enum CommodityStatus {

	/**下架*/
	DOWN(2),
	
	/**上架*/
	UP(3);

	private int value;

	private CommodityStatus(int value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
