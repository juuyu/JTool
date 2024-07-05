package com.nie.tool.common.flowable.oa.model.enums;

import com.nie.tool.common.flowable.oa.config.OaConstEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author njy
 * @since 2024/7/2 08:59
 */
@Getter
@AllArgsConstructor
public enum OaTaskType {

    START(OaConstEnum.TASK_ID_START.getValue(), "初始任务", false),
    NORMAL(OaConstEnum.TASK_ID_NORMAL_PREFIX.getValue(), "普通任务", false),
    LOOP(OaConstEnum.TASK_ID_LOOP_PREFIX.getValue(), "循环任务", false),
    PARALLEL(OaConstEnum.TASK_ID_PARALLEL_PREFIX.getValue(), "并行任务", false),
    SEQUENTIAL(OaConstEnum.TASK_ID_SEQUENTIAL_PREFIX.getValue(), "串行任务", false),
    ;
    private final String  taskIdPrefix;
    private final String  text;
    private final boolean containValue;


    public static OaTaskType parseByTaskDefinitionKey(String taskDefinitionKey) {
        if (taskDefinitionKey != null) {
            for (OaTaskType value : values()) {
                if (taskDefinitionKey.startsWith(value.getTaskIdPrefix())) {
                    return value;
                }
            }
        }
        return OaTaskType.NORMAL;
    }


    public String getContainValueByTaskId(String taskDefinitionKey) {
        if (taskDefinitionKey != null) {
            if (this.containValue) {
                return taskDefinitionKey.replace(this.taskIdPrefix, "");
            }
        }
        return null;
    }
}
