package com.nie.tool.common.core.util.execute;

import java.util.Map;

/**
 * @author njy
 * @since 2024/8/13 13:27
 */
public class ExecuteParam {
    private SupplierThrowable<Object> supplier;
    private boolean                   throwErr;
    private Object                    defaultVal;
    private String                    methodName;
    private Map<String, Object>       param;

}
