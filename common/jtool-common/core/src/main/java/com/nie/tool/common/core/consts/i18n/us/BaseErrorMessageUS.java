package com.nie.tool.common.core.consts.i18n.us;

import com.nie.tool.common.core.annotation.I18nMessage;
import com.nie.tool.common.core.enums.I18nEnum;

/**
 * @author njy
 * @since 2024/8/23 13:10
 */
@I18nMessage(value = I18nEnum.US, prefix = "base_")
public interface BaseErrorMessageUS {
    String lock_failed = "Get lock failed, lockName={}";
}
