package com.nie.tool.common.flowable.common.config;

/**
 * @author njy
 * @since 2024/7/3 14:42
 */
public interface ExecutionEvent {
    /**
     * 流程实例启动
     */
    String EXECUTION_EVENT_START      = "start";
    /**
     * 流程实例结束
     */
    String EXECUTION_EVENT_END        = "end";
    /**
     * 流程实例执行一个序列流
     */
    String EXECUTION_EVENT_TAKE       = "take";
}
