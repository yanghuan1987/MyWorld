package com.spfood.pms.search.intf.utils;
/**
 * 评价显示类型枚举
 * @author Administrator
 *
 */
public enum CommentType {

    /**
     * 显示
     */
    SHOW(1, "显示"),
    /**
     * 不显示
     */
    HIDE(0, "不显示");
    
    
    private int value;
    
    private String comment;
        
    private CommentType(int value, String comment) {
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
