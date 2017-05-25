package com.spfood.cms.intf.utils;


public enum CmsConfiguration {

	BANNER("cms_banner_configure"), PROMOTEDCOMMODITY("cms_commodity_configure"),
	MAX("max"), MIN("min");
	
	private String value;
	
	private CmsConfiguration(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
