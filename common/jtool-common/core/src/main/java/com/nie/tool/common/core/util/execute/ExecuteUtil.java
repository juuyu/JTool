package com.nie.tool.common.core.util.execute;


import org.dromara.hutool.core.thread.ThreadUtil;
import org.dromara.hutool.extra.spring.SpringUtil;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;


/**
 * @author njy
 * @since 2024/8/5 13:06
 */
public class ExecuteUtil {
    private static final AbstractExecute EXECUTE;

    static {
        AbstractExecute bean;
        try {
            bean = SpringUtil.getBean(AbstractExecute.class);
        } catch (Throwable t) {
            bean = null;
        }

        EXECUTE = bean != null ? bean : (new AbstractExecute() {
            @Override
            protected Executor getAsyncExecutor() {
                return ForkJoinPool.commonPool();
            }
        });
    }

    public static AbstractExecute getExecute() {
        return EXECUTE;
    }

    public static <T> T supply(SupplierThrowable<T> supplier, boolean throwErr, T defaultVal, String methodName, Map<String, Object> param) {
        return EXECUTE.supply(supplier, throwErr, defaultVal, methodName, param);
    }

    public static <T> T supply(SupplierThrowable<T> supplier) {
        return supply(supplier, false, null, "", null);
    }

    public static <T> T supply(SupplierThrowable<T> supplier, T defaultVal) {
        return supply(supplier, false, defaultVal, "", null);
    }

    public static <T> T supplyIf(SupplierThrowable<T> supplier, boolean throwErr, T defaultVal, String methodName, boolean expression, T elseValue) {
        if (expression) {
            return supply(supplier, throwErr, defaultVal, methodName, null);
        }

        return elseValue;
    }

    public static <T> T supplyIf(SupplierThrowable<T> supplier, boolean expression) {
        return supplyIf(supplier, false, null, "", expression, null);
    }

    public static <T> T supplyIf(SupplierThrowable<T> supplier, boolean expression, T elseValue) {
        return supplyIf(supplier, false, null, "", expression, elseValue);
    }


    public static void run(RunnableThrowable runnable, boolean throwErr, String methodName, Map<String, Object> param) {
        EXECUTE.run(runnable, throwErr, methodName, param);
    }

    public static void run(RunnableThrowable runnable) {
        run(runnable, false, "", null);
    }

    public static void runIf(RunnableThrowable runnable, boolean throwErr, String methodName, boolean expression) {
        if (expression) {
            run(runnable, throwErr, methodName, null);
        }
    }

    public static void runIf(RunnableThrowable runnable, boolean expression) {
        runIf(runnable, false, "", expression);
    }


    public static void tryRun(SupplierThrowable<Boolean> supplier, String methodName, long gapMillis, int tryCount) {
        for (int i = 0; i < tryCount; i++) {
            Boolean flag = supply(supplier, false, false, methodName, null);
            if (flag) {
                return;
            }

            ThreadUtil.safeSleep(gapMillis);
        }
    }


    /* --------------------------------Async------------------------------- */

    public static <T> CompletableFuture<T> supplyAsync(SupplierThrowable<T> supplier, boolean throwErr, T defaultVal, String methodName, boolean copyContext, Map<String, Object> param) {
        return EXECUTE.supplyAsync(supplier, throwErr, defaultVal, methodName, copyContext, param);
    }

    public static <T> CompletableFuture<T> supplyAsync(SupplierThrowable<T> supplier) {
        return supplyAsync(supplier, false, null, "", true, null);
    }

    public static <T> CompletableFuture<T> supplyAsync(SupplierThrowable<T> supplier, T defaultVal) {
        return supplyAsync(supplier, false, defaultVal, "", true, null);
    }

    public static CompletableFuture<Void> runAsync(RunnableThrowable runnable, boolean throwErr, String methodName, boolean copyContext, Map<String, Object> param) {
        return EXECUTE.runAsync(runnable, throwErr, methodName, copyContext, param);
    }

    public static CompletableFuture<Void> runAsync(RunnableThrowable runnable) {
        return runAsync(runnable, false, "", true, null);
    }

}
