package com.spfood.cms.intf.utils;

public enum PromotedCommodityName {

    HOMEPAGE("主页","cms_commodity_pos_homePage"),
    ORDERPAGE("订单页","cms_commodity_pos_orderPage");
    
    private String name;
    
    private String value;

    private PromotedCommodityName(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
    
    
}
