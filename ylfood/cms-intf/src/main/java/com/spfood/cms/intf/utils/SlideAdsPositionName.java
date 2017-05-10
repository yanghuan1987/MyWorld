package com.spfood.cms.intf.utils;

public enum SlideAdsPositionName {

	/**pc端*/
	PC("PC端"),
	
	/**手机端*/
	PHONE("手机端");

	private String value;

	private SlideAdsPositionName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
