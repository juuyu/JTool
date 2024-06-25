package com.nie.tool.common.core.util;

import cn.hutool.core.util.StrUtil;

import java.util.Collection;
import java.util.Objects;

/**
 * @author njy
 * @since 2024/6/24 14:01
 */
public class AssertUtil {
    private AssertUtil() {
    }

    public static void isTrue(boolean val) {
        if (!val) {
            throw new RuntimeException();
        }
    }

    public static void isTrue(boolean val, String msg) {
        if (!val) {
            throw new RuntimeException(msg);
        }
    }

    public static void isTrue(boolean val, RuntimeException exception) {
        if (!val) {
            throw exception;
        }
    }

    public static void isFalse(boolean val) {
        isTrue(!val);
    }

    public static void isFalse(boolean val, String msg) {
        isTrue(!val, msg);
    }

    public static void isFalse(boolean val, RuntimeException exception) {
        isTrue(!val, exception);
    }

    public static void isNull(Object val) {
        isTrue(Objects.isNull(val));
    }

    public static void isNull(Object val, String msg) {
        isTrue(Objects.isNull(val), msg);
    }

    public static void isNull(Object val, RuntimeException exception) {
        isTrue(Objects.isNull(val), exception);
    }

    public static void noNull(Object val) {
        isFalse(Objects.isNull(val));
    }

    public static void noNull(Object val, String msg) {
        isFalse(Objects.isNull(val), msg);
    }

    public static void noNull(Object val, RuntimeException exception) {
        isFalse(Objects.isNull(val), exception);
    }

    public static void isBlank(String val) {
        isTrue(StrUtil.isBlank(val));
    }

    public static void isBlank(String val, String msg) {
        isTrue(StrUtil.isBlank(val), msg);
    }

    public static void isBlank(String val, RuntimeException exception) {
        isTrue(StrUtil.isBlank(val), exception);
    }

    public static void noBlank(String val) {
        isFalse(StrUtil.isBlank(val));
    }

    public static void noBlank(String val, String msg) {
        isFalse(StrUtil.isBlank(val), msg);
    }

    public static void noBlank(String val, RuntimeException exception) {
        isFalse(StrUtil.isBlank(val), exception);
    }

    public static void isEmpty(Collection<?> val) {
        isTrue(val == null || val.isEmpty());
    }

    public static void isEmpty(Collection<?> val, String msg) {
        isTrue(val == null || val.isEmpty(), msg);
    }

    public static void isEmpty(Collection<?> val, RuntimeException exception) {
        isTrue(val == null || val.isEmpty(), exception);
    }

    public static void noEmpty(Collection<?> val) {
        isFalse(val == null || val.isEmpty());
    }

    public static void noEmpty(Collection<?> val, String msg) {
        isFalse(val == null || val.isEmpty(), msg);
    }

    public static void noEmpty(Collection<?> val, RuntimeException exception) {
        isFalse(val == null || val.isEmpty(), exception);
    }

}
