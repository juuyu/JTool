package com.nie.tool.common.flowable.common.exception;


import com.nie.tool.common.flowable.common.model.enums.FlowableProcessStatus;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author njy
 * @since 2024/7/4 14:13
 */
public class ProcessStatusException extends Exception {
    private static final String ERR_FORMAT = "租户[%s]下processKey=%s的流程状态不正确, 当前状态[%s], 允许执行的状态[%s]";

    public ProcessStatusException() {
    }

    public ProcessStatusException(String processKey, String tenantId, String nowStatus, FlowableProcessStatus[] allowStatus) {
        super(String.format(ERR_FORMAT, tenantId, processKey, nowStatus, convertStatusesToString(allowStatus)));
    }

    private static String convertStatusesToString(FlowableProcessStatus[] allowStatus) {
        if (allowStatus == null || allowStatus.length == 0) {
            return "";
        }
        return Arrays.stream(allowStatus)
                .map(FlowableProcessStatus::name)
                .collect(Collectors.joining(", "));
    }
}
