package com.nie.tool.common.flowable.common.handler;

import com.nie.tool.common.flowable.common.util.FlowableApiExecute;
import jakarta.annotation.Resource;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.Map;

/**
 * @author njy
 * @since 2024/7/3 09:04
 */
public abstract class BaseProcessInstanceStartHandler {

    @Resource
    protected ProcessEngine processEngine;


    /**
     * 启动流程实例
     *
     * @param processKey processKey
     * @param tenantId   tenantId
     */
    public void startProcessInstanceByKeyAndTenantId(String processKey, String tenantId, Map<String, Object> variables) {
        FlowableApiExecute.run(() -> setProcessVariables(processKey, tenantId, variables), processKey, tenantId);
        // 启动一个流程实例
        RuntimeService runtimeService = FlowableApiExecute.supply(() -> processEngine.getRuntimeService());
        ProcessInstance processInstance = FlowableApiExecute.supply(() -> runtimeService
                .startProcessInstanceByKeyAndTenantId(processKey, variables, tenantId), processKey, tenantId);
        FlowableApiExecute.run(() -> afterStartProcessInstance(processInstance.getId(), variables), processKey, tenantId);
    }


    /**
     * 设置流程变量
     *
     * @param processKey processKey
     * @param tenantId   tenantId
     * @param variables  variables
     */
    protected abstract void setProcessVariables(String processKey, String tenantId, Map<String, Object> variables);


    /**
     * 启动流程实例之后
     *
     * @param processInstanceId processInstanceId
     * @param variables         variables
     */
    protected abstract void afterStartProcessInstance(String processInstanceId, Map<String, Object> variables);


}
