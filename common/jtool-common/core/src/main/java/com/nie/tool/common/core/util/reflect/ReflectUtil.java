package com.nie.tool.common.core.util.reflect;

import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author njy
 * @since 2024/8/9 10:07
 */
public class ReflectUtil {
    private static final Map<String, FieldAccess>  FIELD_ACCESS_MAP  = new ConcurrentHashMap<>(64);
    private static final Map<String, MethodAccess> METHOD_ACCESS_MAP = new ConcurrentHashMap<>(64);

    public static FieldAccess getFieldAccess(Class<?> clazz) {
        if (FIELD_ACCESS_MAP.containsKey(clazz.getName())) {
            return FIELD_ACCESS_MAP.get(clazz.getName());
        }
        FieldAccess fieldAccess = null;
        try {
            fieldAccess = FieldAccess.get(clazz);
            return fieldAccess;
        } finally {
            if (fieldAccess != null) {
                FIELD_ACCESS_MAP.put(clazz.getName(), fieldAccess);
            }
        }
    }

    public static MethodAccess getMethodAccess(Class<?> clazz) {
        if (METHOD_ACCESS_MAP.containsKey(clazz.getName())) {
            return METHOD_ACCESS_MAP.get(clazz.getName());
        }
        MethodAccess methodAccess = null;
        try {
            methodAccess = MethodAccess.get(clazz);
            return methodAccess;
        } finally {
            if (methodAccess != null) {
                METHOD_ACCESS_MAP.put(clazz.getName(), methodAccess);
            }
        }
    }

    public static void main(String[] args) {

    }


}
