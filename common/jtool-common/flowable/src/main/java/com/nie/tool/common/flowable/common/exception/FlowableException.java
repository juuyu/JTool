package com.nie.tool.common.flowable.common.exception;

/**
 * @author njy
 * @since 2024/7/1 15:16
 */
public class FlowableException extends RuntimeException {

    private static final String ERR_FORMAT = "调用FlowableApi发生异常, processKey=%s, tenantId=%s";

    public FlowableException() {
    }

    public FlowableException(String message) {
        super(message);
    }

    public FlowableException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowableException(String processKey, String tenantId, Throwable cause) {
        super(String.format(ERR_FORMAT, processKey, tenantId), cause);
    }

    public FlowableException(Throwable cause) {
        super(cause);
    }

    public FlowableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
