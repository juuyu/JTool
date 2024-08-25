package com.nie.tool.common.core.util.execute;

/**
 * @author njy
 * @since 2024/8/1 22:41
 */
@FunctionalInterface
public interface RunnableThrowable {
    /**
     * Runs this operation.
     */
    void run() throws Throwable;
}
