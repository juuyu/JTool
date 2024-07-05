package com.nie.tool.common.flowable.oa.model.dto;

import com.nie.tool.common.flowable.oa.model.enums.OaTaskType;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author njy
 * @since 2024/7/1 17:34
 */
@Data
public class OaTaskDTO {
    /**
     * taskId
     */
    private String     taskId;
    /**
     * 任务类型
     */
    private OaTaskType taskType;
    /**
     * 流程实例id
     */
    private String     processInstanceId;
    private String processDefinitionId;
    /**
     * processKey
     */
    private String processKey;
    private String taskDefinitionKey;
    private String taskName;

    private String assignee;

    /**
     * 持续时间
     */
    private Long durationInMillis;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;


    /**
     * 变量
     */
    private Map<String, Object> variables;

}
