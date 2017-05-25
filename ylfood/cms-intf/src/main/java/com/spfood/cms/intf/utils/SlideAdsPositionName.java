package com.spfood.cms.intf.utils;

public enum SlideAdsPositionName {

	/**pc端*/
	PC("cms_slide_ads_pos_pc"),
	
	/**手机端*/
	PHONE("cms_slide_ads_pos_mobile");

	private String value;

	private SlideAdsPositionName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
