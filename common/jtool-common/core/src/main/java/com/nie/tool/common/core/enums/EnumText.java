package com.nie.tool.common.core.enums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author njy
 * @since 2024/7/22 09:09
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface EnumText {
    String value() default "text";
}
