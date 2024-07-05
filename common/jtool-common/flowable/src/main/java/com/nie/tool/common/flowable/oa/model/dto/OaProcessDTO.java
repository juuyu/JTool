package com.nie.tool.common.flowable.oa.model.dto;

import com.nie.tool.common.flowable.common.dao.entity.FlowableProcessDO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author njy
 * @since 2024/7/2 11:22
 */
@Data
public class OaProcessDTO {
    /**
     * 流程详情
     */
    private FlowableProcessDO          flowableProcessDetail;
    /**
     * 流程实例
     */
    private List<OaProcessInstanceDTO> processInstances = new ArrayList<>();

}
