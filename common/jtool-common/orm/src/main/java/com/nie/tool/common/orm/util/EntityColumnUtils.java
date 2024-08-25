package com.nie.tool.common.orm.util;

/**
 * @author njy
 * @since 2024/7/16 16:05
 */
public class EntityColumnUtils {

    public static Boolean parseFlag(Short val) {
        if (val == null){
            return null;
        }
        return val == 1;
    }

}
