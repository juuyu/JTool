package com.nie.tool.common.flowable.common.util;

import com.nie.tool.common.flowable.common.exception.FlowableException;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * @author njy
 * @since 2024/7/4 14:39
 */
@Slf4j
public class FlowableApiExecute {

    private static final String ERR_MSG = "调用FlowableApi发生异常";


    public static void run(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable t) {
            throw new FlowableException(ERR_MSG, t);
        }
    }

    public static void run(Runnable runnable, String processKey, String tenantId) {
        run(runnable, true, processKey, tenantId);
    }

    public static void run(Runnable runnable, boolean throwErr, String processKey, String tenantId) {
        try {
            runnable.run();
        } catch (Throwable t) {
            if (throwErr) {
                throw new FlowableException(processKey, tenantId, t);
            } else {
                log.warn("调用FlowableApi发生异常", t);
            }
        }
    }


    public static <U> U supply(Supplier<U> supplier) {
        try {
            return supplier.get();
        } catch (Throwable t) {
            throw new FlowableException(ERR_MSG, t);
        }
    }

    public static <U> U supply(Supplier<U> supplier, String processKey, String tenantId) {
        return supply(supplier, true, processKey, tenantId);
    }

    public static <U> U supply(Supplier<U> supplier, boolean throwErr, String processKey, String tenantId) {
        try {
            return supplier.get();
        } catch (Throwable t) {
            if (throwErr) {
                throw new FlowableException(processKey, tenantId, t);
            } else {
                log.warn("调用FlowableApi发生异常", t);
                return null;
            }
        }
    }

}
