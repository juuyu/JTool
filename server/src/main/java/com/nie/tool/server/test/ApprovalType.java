package com.nie.tool.server.test;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author njy
 * @since 2024/7/22 09:21
 */
@AllArgsConstructor
@Getter
public enum ApprovalType {
    TYPE_A("Type A Text"),
    TYPE_B("Type B Text");

    private final String text;
}
