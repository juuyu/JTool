package com.nie.tool.common.core.consts.i18n.cn;

import com.nie.tool.common.core.annotation.I18nMessage;
import com.nie.tool.common.core.enums.I18nEnum;

/**
 * @author njy
 * @since 2024/8/23 13:10
 */
@I18nMessage(value = I18nEnum.CN, prefix = "base_")
public interface BaseErrorMessageCN {
    String lock_failed = "获取锁失败, lockName={}";
}
