package com.nie.tool.common.flowable.oa.model.dto;

import com.nie.tool.common.flowable.common.config.FlowableVariableKey;
import com.nie.tool.common.flowable.common.util.FlowableVariable;
import com.nie.tool.common.flowable.oa.config.OaConstEnum;
import lombok.Data;

/**
 * @author njy
 * @since 2024/7/1 16:55
 */
@Data
public class ApplyStartDTO {
    /**
     * 流程key
     */
    private String           processKey;
    /**
     * 租户id
     */
    private String           tenantId;
    /**
     * 开启流程用户身份id
     */
    private String           applyUserId;
    /**
     * 自定义流程变量
     */
    private FlowableVariable variable = new FlowableVariable();


    public FlowableVariable buildFlowableVariable() {
        return getVariable()
                .put(FlowableVariableKey.TENANT_ID, getTenantId())
                .put(OaConstEnum.VAR_KEY_START_USER_ID.getValue(), getApplyUserId())
                .put(OaConstEnum.VAR_KEY_START_TASK_ASSIGNEE.getValue(), getApplyUserId());
    }
}
