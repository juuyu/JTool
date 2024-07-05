package com.nie.tool.common.flowable.oa.model.dto;

import com.nie.tool.common.flowable.oa.model.enums.OaProcessStatus;
import lombok.Data;

import java.util.*;

/**
 * @author njy
 * @since 2024/7/2 14:21
 */
@Data
public class OaProcessInstanceDTO {
    /**
     * 流程实例id
     */
    private String          instanceId;
    /**
     * 流程定义id
     */
    private String          processDefinitionId;
    /**
     * 结束id
     */
    private String          endActivityId;
    /**
     * 流程持续时间
     */
    private Long            durationInMillis;
    /**
     * 流程实例开始时间
     */
    private Date            startTime;
    /**
     * 流程实例结束时间
     */
    private Date            endTime;
    /**
     * 流程实例状态
     */
    private OaProcessStatus processStatus;
    /**
     * 任务详情
     */
    private List<OaTaskDTO> taskDetails = new ArrayList<>();

    /**
     * 变量
     */
    private Map<String, Object> variables = new HashMap<>();

}
