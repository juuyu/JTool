package com.nie.tool.common.core.util.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author njy
 * @since 2024/8/8 12:41
 */
public class ContextHolderUtil {
    private static final Map<String, BaseContextHolder> HOLDER_MAP = new ConcurrentHashMap<>(64);

    public static List<BaseContextHolder> getHolders() {
        return new ArrayList<>(HOLDER_MAP.values());
    }

    public static void register(BaseContextHolder holder) {
        if (holder != null) {
            HOLDER_MAP.put(holder.getContextKey(), holder);
        }
    }

    public static void destroy(String key) {
        HOLDER_MAP.get(key).remove();
        HOLDER_MAP.remove(key);
    }

    public static BaseContextHolder getHolder(String key) {
        if (HOLDER_MAP.containsKey(key)) {
            return HOLDER_MAP.get(key);
        }
        BaseContextHolder holder = () -> key;
        register(holder);
        return holder;
    }

    public static Map<String, String> getThreadLocalValues() {
        Map<String, String> map = new HashMap<>();
        HOLDER_MAP.forEach((k, v) -> {
            if (v.threadLocal()) {
                map.put(k, v.get());
            }
        });
        return map;
    }

    public static void setValues(Map<String, String> map) {
        map.forEach((k, v) -> getHolder(k).set(v));
    }

    public static void removeThreadLocalValues() {
        HOLDER_MAP.forEach((k, v) -> {
            if (v.threadLocal()) {
                v.remove();
            }
        });
    }

    public static void set(String key, String value) {
        getHolder(key).set(value);
    }

    public static String get(String key) {
        return getHolder(key).get();
    }

    public static void remove(String key) {
        getHolder(key).remove();
    }

}
