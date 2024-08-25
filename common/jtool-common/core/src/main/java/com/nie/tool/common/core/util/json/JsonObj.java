package com.nie.tool.common.core.util.json;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author njy
 * @since 2024/8/19 14:17
 */
public class JsonObj {
    private final Map<String, Object> rawMap = new LinkedHashMap<>(64);

    public static JsonObj build() {
        return new JsonObj();
    }

    public JsonObj() {
    }

    public JsonObj(Map<String, Object> map) {
        if (map != null) {
            rawMap.putAll(map);
        }
    }

    public JsonObj put(String key, Object value) {
        rawMap.put(key, value);
        return this;
    }

    public Object get(String key) {
        return rawMap.get(key);
    }

    public Map<String, Object> getRawMap() {
        return rawMap;
    }

    public String toJsonStr() {
        return JsonUtil.obj2String(getRawMap());
    }

}
