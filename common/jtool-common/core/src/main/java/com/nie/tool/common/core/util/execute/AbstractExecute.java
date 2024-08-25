package com.nie.tool.common.core.util.execute;

import com.nie.tool.common.core.util.context.ContextHolderUtil;
import org.dromara.hutool.core.lang.Console;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author njy
 * @since 2024/8/7 16:41
 */
public abstract class AbstractExecute {


    /**
     * beforeExecute
     *
     * @param param param
     * @return boolean
     */
    protected boolean beforeExecute(Map<String, Object> param) {
        return true;
    }


    /**
     * afterExecute
     *
     * @param param     param
     * @param err       err
     * @param result    result
     * @param throwable throwable
     */
    protected <T> void afterExecute(Map<String, Object> param, boolean err, T result, Throwable throwable) {
    }

    /**
     * beforeAsyncExecute
     */
    protected boolean beforeAsyncExecute(Map<String, Object> param) {
        return true;
    }

    /**
     * beforeAsyncExecute
     */
    protected <T> void afterAsyncExecute(Map<String, Object> param, boolean err, T result, Throwable throwable) {
    }

    /**
     * getAsyncExecutor
     *
     * @return java.util.concurrent.Executor
     */
    protected abstract Executor getAsyncExecutor();

    /**
     * supply
     *
     * @param supplier   supplier
     * @param throwErr   throwErr
     * @param defaultVal defaultVal
     * @param methodName methodName
     * @param param      param
     * @return T
     */
    public <T> T supply(final SupplierThrowable<T> supplier,
                        final boolean throwErr,
                        final T defaultVal,
                        final String methodName,
                        final Map<String, Object> param) {
        T result = null;
        Throwable throwable = null;
        boolean err = false;

        try {
            if (beforeExecute(param))
                result = supplier.get();
            else
                result = defaultVal;

            return result;
        } catch (Throwable t) {
            err = true;
            throwable = t;

            if (throwErr)
                throw new RuntimeException(t);

            Console.error(t, "Execute method[{}] err: ", methodName);
            return defaultVal;
        } finally {
            afterExecute(param, err, result, throwable);
        }
    }


    /**
     * run
     *
     * @param runnable   runnable
     * @param throwErr   throwErr
     * @param methodName methodName
     * @param param      param
     */
    public void run(final RunnableThrowable runnable,
                    final boolean throwErr,
                    final String methodName,
                    final Map<String, Object> param) {
        Throwable throwable = null;
        boolean err = false;

        try {
            if (beforeExecute(param))
                runnable.run();
        } catch (Throwable t) {
            err = true;
            throwable = t;

            if (throwErr)
                throw new RuntimeException(t);

            Console.error(t, "Execute method[{}] err: ", methodName);
        } finally {
            afterExecute(param, err, null, throwable);
        }
    }


    /* --------------------------------Async------------------------------- */


    /**
     * supplyAsync
     *
     * @param supplier    supplier
     * @param throwErr    throwErr
     * @param defaultVal  defaultVal
     * @param methodName  methodName
     * @param copyContext copyContext
     * @return java.util.concurrent.CompletableFuture<T>
     */
    public <T> CompletableFuture<T> supplyAsync(final SupplierThrowable<T> supplier,
                                                final boolean throwErr,
                                                final T defaultVal,
                                                final String methodName,
                                                final boolean copyContext,
                                                final Map<String, Object> param) {
        Map<String, String> contextMap = copyContext ? ContextHolderUtil.getThreadLocalValues() : null;

        return CompletableFuture.supplyAsync(() -> {
            T result = null;
            Throwable throwable = null;
            boolean err = false;

            try {
                if (copyContext)
                    ContextHolderUtil.setValues(contextMap);

                if (beforeAsyncExecute(param))
                    result = supplier.get();
                else
                    result = defaultVal;

                return result;
            } catch (Throwable t) {
                err = true;
                throwable = t;

                if (throwErr)
                    throw new RuntimeException(t);

                Console.error(t, "Execute method[{}] err: ", methodName);
                return defaultVal;
            } finally {
                if (copyContext)
                    ContextHolderUtil.removeThreadLocalValues();

                afterAsyncExecute(param, err, result, throwable);
            }
        }, getAsyncExecutor());
    }

    /**
     * runAsync
     *
     * @param runnable    runnable
     * @param throwErr    throwErr
     * @param methodName  methodName
     * @param copyContext copyContext
     * @param param       param
     * @return java.util.concurrent.CompletableFuture<java.lang.Void>
     */
    public CompletableFuture<Void> runAsync(final RunnableThrowable runnable,
                                            final boolean throwErr,
                                            final String methodName,
                                            final boolean copyContext,
                                            final Map<String, Object> param) {
        Map<String, String> contextMap = copyContext ? ContextHolderUtil.getThreadLocalValues() : null;

        return CompletableFuture.runAsync(() -> {
            Throwable throwable = null;
            boolean err = false;

            try {
                if (copyContext)
                    ContextHolderUtil.setValues(contextMap);

                if (beforeAsyncExecute(param))
                    runnable.run();

            } catch (Throwable t) {
                err = true;
                throwable = t;

                if (throwErr)
                    throw new RuntimeException(t);

                Console.error(t, "Execute method[{}] err: ", methodName);
            } finally {
                if (copyContext)
                    ContextHolderUtil.removeThreadLocalValues();

                afterAsyncExecute(param, err, null, throwable);
            }
        }, getAsyncExecutor());
    }
}
