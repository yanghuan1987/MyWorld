package com.spfood.pms.search.intf.utils;

public class StringUtils {

    //字符串不为null，不为空字符串和纯空格
    public static boolean testNull(String string) {
        if (string==null||string.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
