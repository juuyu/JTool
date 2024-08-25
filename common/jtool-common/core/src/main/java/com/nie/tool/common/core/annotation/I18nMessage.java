package com.nie.tool.common.core.annotation;


import com.nie.tool.common.core.enums.I18nEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author njy
 * @since 2024/8/21 16:14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface I18nMessage {
    /**
     * lang
     */
    I18nEnum value() default I18nEnum.CN;

    /**
     * 前缀
     */
    String prefix() default "";
}
