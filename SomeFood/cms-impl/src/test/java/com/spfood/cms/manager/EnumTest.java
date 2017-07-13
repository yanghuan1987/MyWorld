package com.spfood.cms.manager;

public enum EnumTest {
	SAY("说"),
	doit("zuo");
	private String valuString;
	private EnumTest(String value) {
		this.valuString = value;
	}
	public String getValue() {
		return this.valuString;
	}
	
}
