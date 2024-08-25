package com.nie.tool.common.web.annotation;

import java.lang.annotation.*;

/**
 * @author njy
 * @since 2024/7/16 13:31
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RestController {
}
