package com.nie.tool.common.flowable.common.model.enums;

/**
 * @author njy
 * @since 2024/7/2 10:52
 */
public enum FlowableProcessStatus {

    INIT,
    START,
    STOP;

    public static FlowableProcessStatus parse(String val) {
        for (FlowableProcessStatus value : values()) {
            if (value.name().equals(val)) {
                return value;
            }
        }
        return null;
    }
}
