package com.nie.tool.common.flowable.oa.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author njy
 * @since 2024/7/2 09:13
 */
@Getter
@AllArgsConstructor
public enum OaConstEnum {


    /**
     * task
     */
    TASK_ID_START("oa_task_start_", "oa审批流程初始任务固定id", OaConstType.TASK),
    TASK_ID_NORMAL_PREFIX("oa_task_normal_", "oa审批流程普通任务id前缀", OaConstType.TASK),
    TASK_ID_LOOP_PREFIX("oa_task_loop_", "oa审批流程循环任务id前缀", OaConstType.TASK),
    TASK_ID_PARALLEL_PREFIX("oa_task_parallel_", "oa审批流程并行任务id前缀", OaConstType.TASK),
    TASK_ID_SEQUENTIAL_PREFIX("oa_task_sequential_", "oa审批流程串行任务id前缀", OaConstType.TASK),
    /**
     * variable
     */
    VAR_KEY_START_USER_ID("start_user_id", "启动流程用户变量", OaConstType.VARIABLE),
    VAR_KEY_APPROVE_RESULT("approval_result", "审批结果变量", OaConstType.VARIABLE),
    VAR_KEY_START_TASK_ASSIGNEE("start_task_assignee", "初始任务分配人", OaConstType.VARIABLE),
    VAR_KEY_LOOP_TASK_ASSIGNEE("loop_task_assignee", "循环任务分配人", OaConstType.VARIABLE),
    VAR_KEY_PARALLEL_TASK_ASSIGNEE("parallel_task_assignee_list", "并行任务分配", OaConstType.VARIABLE),
    VAR_KEY_SEQUENTIAL_TASK_ASSIGNEE("sequential_task_assignee_list", "串行流程分配", OaConstType.VARIABLE),
    ;

    private final String      value;
    private final String      text;
    private final OaConstType type;
}
