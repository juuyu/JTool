package com.nie.tool.common.flowable.oa.service.impl;

import com.nie.tool.common.flowable.common.annotation.ProcessCheck;
import com.nie.tool.common.flowable.common.exception.ProcessNotFoundException;
import com.nie.tool.common.flowable.common.exception.ProcessStatusException;
import com.nie.tool.common.flowable.common.manager.FlowableGroupRelationManager;
import com.nie.tool.common.flowable.common.model.enums.FlowableProcessStatus;
import com.nie.tool.common.flowable.common.util.FlowableApiExecute;
import com.nie.tool.common.flowable.common.util.FlowableVariable;
import com.nie.tool.common.flowable.oa.config.OaConstEnum;
import com.nie.tool.common.flowable.oa.model.dto.OaBaseQueryDTO;
import com.nie.tool.common.flowable.oa.model.dto.OaTaskDTO;
import com.nie.tool.common.flowable.oa.model.enums.ApprovalResult;
import com.nie.tool.common.flowable.oa.service.OaTaskService;
import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author njy
 * @since 2024/7/1 17:31
 */
@Service
@RequiredArgsConstructor
public class OaTaskServiceImpl implements OaTaskService {
    private final FlowableGroupRelationManager flowableGroupRelationManager;
    private final TaskService                  taskService;
    private final RuntimeService               runtimeService;

//    @Override
//    public List<TaskDetailDTO> listUserApplyTask(OaBaseQueryDTO oaBaseQueryDTO) {
//        List<HistoricProcessInstance> list = historyService
//                .createHistoricProcessInstanceQuery()
//                .processDefinitionKey(oaBaseQueryDTO.getProcessKey())
//                .variableValueEquals(OaConstEnum.VAR_KEY_START_USER_ID.getValue(), oaBaseQueryDTO.getUserId())
//                .orderByProcessInstanceStartTime()
//                .desc()
//                .list();
//
//
//        return null;
//    }

    @Override
    @ProcessCheck(throwException = true, checkStatus = true, allowExecuteStatus = {FlowableProcessStatus.START, FlowableProcessStatus.STOP})
    public List<OaTaskDTO> listUserNeedApproveTask(OaBaseQueryDTO oaBaseQueryDTO) throws ProcessNotFoundException, ProcessStatusException {
        List<Task> tasks = FlowableApiExecute.supply(() -> taskService.createTaskQuery()
                .processDefinitionKeyIn(Collections.singletonList(oaBaseQueryDTO.getProcessKey()))
                .taskTenantId(oaBaseQueryDTO.getTenantId())
                .taskAssignee(oaBaseQueryDTO.getUserId())
                .list(), oaBaseQueryDTO.getProcessKey(), oaBaseQueryDTO.getTenantId());
        if (CollectionUtil.isEmpty(tasks)) {
            return new ArrayList<>();
        }
        return tasks.stream().map(e -> {
            OaTaskDTO detailDTO = new OaTaskDTO();
            detailDTO.setTaskId(e.getId());
            detailDTO.setProcessInstanceId(e.getProcessInstanceId());
            detailDTO.setProcessDefinitionId(e.getProcessDefinitionId());
            detailDTO.setTaskDefinitionKey(e.getTaskDefinitionKey());
            detailDTO.setTaskName(e.getName());
            detailDTO.setAssignee(e.getAssignee());

            // 获取流程实例对象
            ProcessInstance processInstance = FlowableApiExecute.supply(() -> runtimeService.createProcessInstanceQuery()
                    .processInstanceId(detailDTO.getProcessInstanceId())
                    .singleResult(), oaBaseQueryDTO.getProcessKey(), oaBaseQueryDTO.getTenantId());
            detailDTO.setProcessKey(processInstance.getProcessDefinitionKey());
            detailDTO.setVariables(taskService.getVariables(e.getId()));
            return detailDTO;
        }).collect(Collectors.toList());
    }


    @Override
    public List<OaTaskDTO> listUserApprovedTask(OaBaseQueryDTO oaBaseQueryDTO) {
        return null;
    }


    @Override
    public void pass(String taskId, FlowableVariable variable) {
        variable.put(OaConstEnum.VAR_KEY_APPROVE_RESULT.getValue(), ApprovalResult.PASS.getCode());
        FlowableApiExecute.run(() -> {
            taskService.setVariablesLocal(taskId, variable.getRawHashMap());
            taskService.complete(taskId, variable.getRawHashMap());
        });
    }

    @Override
    public void reject(String taskId, FlowableVariable variable) {
        variable.put(OaConstEnum.VAR_KEY_APPROVE_RESULT.getValue(), ApprovalResult.REJECT.getCode());
        FlowableApiExecute.run(() -> {
            taskService.setVariablesLocal(taskId, variable.getRawHashMap());
            taskService.complete(taskId, variable.getRawHashMap());
        });
    }

}
