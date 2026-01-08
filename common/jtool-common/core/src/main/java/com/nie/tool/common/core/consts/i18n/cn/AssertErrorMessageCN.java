package com.nie.tool.common.core.consts.i18n.cn;

import com.nie.tool.common.core.annotation.I18nMessage;
import com.nie.tool.common.core.enums.I18nEnum;

/**
 * @author njy
 * @since 2024/8/22 15:30
 */
@I18nMessage(value = I18nEnum.CN, prefix = "util_assert_")
public interface AssertErrorMessageCN {
    String is_true        = "[断言失败] - 此表达式必须为 true";
    String is_false       = "[断言失败] - 此表达式必须为 false";
    String is_null        = "[断言失败] - 对象参数必须为 null";
    String not_null       = "[断言失败] - 此参数是必需的;它不能为 null";
    String not_empty_str  = "[断言失败] - 此 String 参数必须具有长度;它不能为 null 或为空";
    String not_blank      = "[断言失败] - 此 String 参数必须包含文本;它不能为 null、空或空白";
    String not_empty_coll = "[断言失败] - 此集合不能为空;它必须至少包含 1 个元素";
    String not_empty_map  = "[断言失败] - 此Map不能为空;它必须至少包含一个条目";
    String not_equals     = "[断言失败] - ({}) 不能 equals ({})";
    String is_equals      = "[断言失败] - ({}) 必须 equals ({})";
}
