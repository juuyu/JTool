package com.nie.tool.common.core.util.execute;

/**
 * @author njy
 * @since 2024/7/31 15:16
 */
@FunctionalInterface
public interface SupplierThrowable<T> {
    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws Throwable;
}
