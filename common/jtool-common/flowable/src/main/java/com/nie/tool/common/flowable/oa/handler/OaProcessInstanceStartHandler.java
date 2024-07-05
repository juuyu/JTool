package com.nie.tool.common.flowable.oa.handler;


import com.nie.tool.common.flowable.common.handler.BaseProcessInstanceStartHandler;
import com.nie.tool.common.flowable.common.manager.FlowableGroupRelationManager;
import jakarta.annotation.Resource;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.MultiInstanceLoopCharacteristics;
import org.flowable.bpmn.model.UserTask;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author njy
 * @since 2024/7/3 09:12
 */
@Component("oaProcessInstanceStartHandler")
public class OaProcessInstanceStartHandler extends BaseProcessInstanceStartHandler {

    @Resource
    private FlowableGroupRelationManager flowableGroupRelationManager;

    @Override
    protected void setProcessVariables(String processKey, String tenantId, Map<String, Object> variables) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionTenantId(tenantId)
                .processDefinitionKey(processKey)
                .latestVersion()
                .singleResult();

        // 获取流程定义的所有任务
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
        Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();

        for (FlowElement flowElement : flowElements) {
            if (flowElement instanceof UserTask) {
                UserTask userTask = (UserTask) flowElement;
                MultiInstanceLoopCharacteristics loopCharacteristics = userTask.getLoopCharacteristics();
                if (loopCharacteristics == null) {
                    continue;
                }
                // 拿到定义的多实例key
                String assignGroupKey = loopCharacteristics.getInputDataItem();
                // 通过key获取实际用户信息
                List<String> assignUsers = flowableGroupRelationManager.listIdentityIdByGroupKey(tenantId, assignGroupKey);
                if (assignUsers.isEmpty()) {
                    continue;
                }
                // 设置变量
                variables.put(assignGroupKey, assignUsers);
            }
        }
    }

    @Override
    protected void afterStartProcessInstance(String processInstanceId, Map<String, Object> variables) {
        // 完成初始任务
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId).singleResult();
        taskService.setVariablesLocal(task.getId(), variables);
        taskService.complete(task.getId());
    }
}
