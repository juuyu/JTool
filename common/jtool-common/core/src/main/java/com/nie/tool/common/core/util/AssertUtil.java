//package com.nie.tool.common.core.util;
//
//
//import java.util.Map;
//
///**
// * @author njy
// * @since 2024/8/21 14:56
// */
//public class AssertUtil {
//    private AssertUtil() {
//    }
//
//    public static void isTrue(boolean expression, String errorMsgTemplate, Object... params) {
//        if (!expression) {
//            throw BusinessException.build(errorMsgTemplate, params);
//        }
//    }
//
//    public static void isTrue(boolean expression) {
//        isTrue(expression, AssertErrorConst.IS_TRUE);
//    }
//
//    public static void isFalse(boolean expression, String errorMsgTemplate, Object... params) {
//        if (expression) {
//            throw BusinessException.build(errorMsgTemplate, params);
//        }
//    }
//
//    public static void isFalse(boolean expression) {
//        isFalse(expression, AssertErrorConst.IS_FALSE);
//    }
//
//    public static void isNull(Object object, String errorMsgTemplate, Object... params) {
//        if (object != null) {
//            throw BusinessException.build(errorMsgTemplate, params);
//        }
//    }
//
//    public static void isNull(Object object) {
//        isNull(object, AssertErrorConst.IS_NULL);
//    }
//
//    public static void notNull(Object object, String errorMsgTemplate, Object... params) {
//        if (object == null) {
//            throw BusinessException.build(errorMsgTemplate, params);
//        }
//    }
//
//    public static void notNull(Object object) {
//        notNull(object, AssertErrorConst.NOT_NULL);
//    }
//
//    public static <T extends CharSequence> void notEmpty(T text, String errorMsgTemplate, Object... params) {
//        if (StringUtil.isEmpty(text)) {
//            throw BusinessException.build(errorMsgTemplate, params);
//        }
//    }
//
//    public static <T extends CharSequence> void notEmpty(T text) {
//        notEmpty(text, AssertErrorConst.NOT_EMPTY_STR);
//    }
//
//    public static <T extends CharSequence> void notBlank(T text, String errorMsgTemplate, Object... params) {
//        if (StringUtil.isBlank(text)) {
//            throw BusinessException.build(errorMsgTemplate, params);
//        }
//    }
//
//    public static <T extends CharSequence> void notBlank(T text) {
//        notBlank(text, AssertErrorConst.NOT_BLANK);
//    }
//
//    public static <E, T extends Iterable<E>> void notEmpty(T collection, String errorMsgTemplate, Object... params) {
//        if (CollUtil.isEmpty(collection)) {
//            throw BusinessException.build(errorMsgTemplate, params);
//        }
//    }
//
//    public static <E, T extends Iterable<E>> void notEmpty(T collection) {
//        notEmpty(collection, AssertErrorConst.NOT_EMPTY_COLL);
//    }
//
//    public static <K, V, T extends Map<K, V>> void notEmpty(T map, String errorMsgTemplate, Object... params) {
//        if (MapUtil.isEmpty(map)) {
//            throw BusinessException.build(errorMsgTemplate, params);
//        }
//    }
//
//    public static <K, V, T extends Map<K, V>> void notEmpty(T map) {
//        notEmpty(map, AssertErrorConst.NOT_EMPTY_MAP);
//    }
//
//    public static void notEquals(Object obj1, Object obj2, String errorMsgTemplate, Object... params) {
//        if (ObjectUtil.equals(obj1, obj2)) {
//            throw BusinessException.build(errorMsgTemplate, params);
//        }
//    }
//
//    public static void notEquals(Object obj1, Object obj2) {
//        notEquals(obj1, obj2, AssertErrorConst.NOT_EQUALS, obj1, obj2);
//    }
//
//    public static void equals(Object obj1, Object obj2, String errorMsgTemplate, Object... params) {
//        if (ObjectUtil.notEqual(obj1, obj2)) {
//            throw BusinessException.build(errorMsgTemplate, params);
//        }
//    }
//
//    public static void equals(Object obj1, Object obj2) {
//        equals(obj1, obj2, AssertErrorConst.EQUALS, obj1, obj2);
//    }
//
//}
