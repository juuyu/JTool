package com.nie.tool.common.flowable.common.config;

/**
 * @author njy
 * @since 2024/7/3 14:42
 */
public interface TaskEvent {

    /**
     * 用户任务创建
     */
    String TASK_EVENT_CREATE   = "create";
    /**
     * 用户任务分配
     */
    String TASK_EVENT_ASSIGN   = "assignment";
    /**
     * 用户任务完成
     */
    String TASK_EVENT_COMPLETE = "complete";
    /**
     * 用户任务删除
     */
    String TASK_EVENT_DELETE   = "delete";

}
