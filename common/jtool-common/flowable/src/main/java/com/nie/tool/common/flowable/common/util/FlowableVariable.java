package com.nie.tool.common.flowable.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author njy
 * @since 2024/7/1 15:20
 */
public class FlowableVariable {

    private final Map<String, Object> rawHashMap;

    public FlowableVariable() {
        rawHashMap = new HashMap<>();
    }

    public FlowableVariable(Map<String, Object> map) {
        rawHashMap = map != null ? map : new HashMap<>();
    }

    public FlowableVariable put(String key, Object val) {
        rawHashMap.put(key, val);
        return this;
    }

    public FlowableVariable put(Map<String, Object> map) {
        if (map != null && !map.isEmpty()) {
            rawHashMap.putAll(map);
        }
        return this;
    }

    public Object getValue(String key) {
        return rawHashMap.get(key);
    }

    public Map<String, Object> getRawHashMap() {
        return rawHashMap;
    }
}
