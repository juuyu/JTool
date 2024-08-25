package com.nie.tool.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Locale;

/**
 * @author njy
 * @since 2024/8/21 13:29
 */
@Getter
@AllArgsConstructor
public enum I18nEnum {
    CN("zh_CN", "zh-CN", Locale.CHINA),
    EN("en_US", "en-US", Locale.US),
    JP("ja_JP", "ja-JP", Locale.JAPAN),
    KR("ko_KR", "ko-KR", Locale.KOREA);

    private final String name;
    private final String headerName;
    private final Locale locale;

    public static I18nEnum parseByHeaderName(String headerName) {
        return parseByHeaderName(headerName, I18nEnum.CN);
    }

    public static I18nEnum parseByHeaderName(String headerName, I18nEnum defaultVal) {
        for (I18nEnum value : values()) {
            if (value.getHeaderName().equals(headerName)) {
                return value;
            }
        }
        return defaultVal;
    }
}
