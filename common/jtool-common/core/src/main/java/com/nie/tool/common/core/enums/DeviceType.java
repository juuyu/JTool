package com.nie.tool.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author njy
 * @since 2024/8/15 14:11
 */
@Getter
@AllArgsConstructor
public enum DeviceType {

    /**
     * pc端
     */
    PC("pc"),

    /**
     * app端
     */
    APP("app"),

    /**
     * 小程序端
     */
    XCX("xcx");

    private final String device;
}
