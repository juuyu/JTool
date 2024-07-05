package com.nie.tool.common.flowable.common.exception;

/**
 * @author njy
 * @since 2024/7/4 14:09
 */
public class ProcessNotFoundException extends Exception {
    private static final String ERR_FORMAT = "租户[%s]下processKey=%s的流程不存在";

    public ProcessNotFoundException() {
    }

    public ProcessNotFoundException(String processKey, String tenantId) {
        super(String.format(ERR_FORMAT, tenantId, processKey));
    }
}
