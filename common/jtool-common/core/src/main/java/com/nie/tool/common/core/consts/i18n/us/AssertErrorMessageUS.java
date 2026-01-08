package com.nie.tool.common.core.consts.i18n.us;

import com.nie.tool.common.core.annotation.I18nMessage;
import com.nie.tool.common.core.enums.I18nEnum;

/**
 * @author njy
 * @since 2024/8/22 15:30
 */
@I18nMessage(value = I18nEnum.US, prefix = "util_assert_")
public interface AssertErrorMessageUS {
    String is_true        = "[Assertion failed] - this expression must be true";
    String is_false       = "[Assertion failed] - this expression must be false";
    String is_null        = "[Assertion failed] - the object argument must be null";
    String not_null       = "[Assertion failed] - this argument is required; it must not be null";
    String not_empty_str  = "[Assertion failed] - this String argument must have length; it must not be null or empty";
    String not_blank      = "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank";
    String not_empty_coll = "[Assertion failed] - this collection must not be empty: it must contain at least 1 element";
    String not_empty_map  = "[Assertion failed] - this map must not be empty; it must contain at least one entry";
    String not_equals     = "[Assertion failed] - ({}) must be not equals ({})";
    String is_equals      = "[Assertion failed] - ({}) must be equals ({})";
}
