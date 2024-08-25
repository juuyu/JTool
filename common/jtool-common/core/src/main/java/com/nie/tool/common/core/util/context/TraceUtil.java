package com.nie.tool.common.core.util.context;


import org.dromara.hutool.core.data.id.UUID;

/**
 * @author njy
 * @since 2024/8/2 11:08
 */
public class TraceUtil {

    static {
        ContextHolderUtil.register(TraceUtil::getTraceIdKey);
    }

    public static String getTraceIdKey() {
        return "trace_id";
    }

    public static String generateTraceId() {
        return UUID.fastUUID().toString(true);
    }

    public static String initTraceId() {
        setTraceId(generateTraceId());
        return getTraceId();
    }

    public static void setTraceId(String traceId) {
        ContextHolderUtil.set(getTraceIdKey(), traceId);
    }

    public static String getTraceId() {
        return ContextHolderUtil.get(getTraceIdKey());
    }

    public static void removeTraceId() {
        ContextHolderUtil.remove(getTraceIdKey());
    }

}
