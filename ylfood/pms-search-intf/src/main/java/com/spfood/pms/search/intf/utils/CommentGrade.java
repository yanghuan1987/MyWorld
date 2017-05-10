package com.spfood.pms.search.intf.utils;

public enum CommentGrade {

	GOOD(1, "goodComment"), MEDIUM(2, "mediumComment"), BAD(3, "badComment"), COUNT(4, "countComment");
	
	
	private int value;
	
	private String comment;
		
	private CommentGrade(int value, String comment) {
		this.value = value;
		this.comment = comment;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getComment() {
		return comment;
	}
}
