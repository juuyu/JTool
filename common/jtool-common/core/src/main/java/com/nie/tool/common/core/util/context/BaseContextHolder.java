package com.nie.tool.common.core.util.context;

import org.slf4j.MDC;

/**
 * @author njy
 * @since 2024/8/2 11:27
 */
@FunctionalInterface
public interface BaseContextHolder {

    /**
     * getContextKey
     *
     * @return java.lang.String
     */
    String getContextKey();


    /**
     * 线程私有
     *
     * @return boolean
     */
    default boolean threadLocal() {
        return true;
    }

    /**
     * set
     *
     * @param val val
     */
    default void set(String val) {
        MDC.put(getContextKey(), val);
    }

    /**
     * get
     *
     * @return java.lang.String
     */
    default String get() {
        return MDC.get(getContextKey());
    }

    /**
     * remove
     */
    default void remove() {
        MDC.remove(getContextKey());
    }

}
