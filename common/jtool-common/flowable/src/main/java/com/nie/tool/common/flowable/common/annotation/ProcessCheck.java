package com.nie.tool.common.flowable.common.annotation;


import com.nie.tool.common.flowable.common.model.enums.FlowableProcessStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author njy
 * @since 2024/7/3 11:22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcessCheck {

    /**
     * 是否抛出异常
     */
    boolean throwException() default false;

    /**
     * 是否检查流程状态
     */
    boolean checkStatus() default false;

    /**
     * 允许的流程状态
     */
    FlowableProcessStatus[] allowExecuteStatus() default {FlowableProcessStatus.START};
}
