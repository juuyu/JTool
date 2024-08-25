package com.nie.tool.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author njy
 * @since 2024/8/15 09:31
 */
@Getter
@AllArgsConstructor
public enum YesNoFlag {
    YES("1", true),
    NO("0", false);

    private final String  text;
    private final Boolean flag;


    public static YesNoFlag parse(String str) {
        return parse(str, YesNoFlag.NO);
    }

    public static YesNoFlag parse(String str, YesNoFlag defaultVal) {
        if (YesNoFlag.YES.text.equals(str)) {
            return YesNoFlag.YES;
        } else if (YesNoFlag.NO.text.equals(str)) {
            return YesNoFlag.NO;
        } else {
            return defaultVal;
        }
    }

}
