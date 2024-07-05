package com.nie.tool.common.flowable.oa.service.impl;

import com.nie.tool.common.flowable.common.annotation.ProcessCheck;
import com.nie.tool.common.flowable.common.exception.ProcessNotFoundException;
import com.nie.tool.common.flowable.common.exception.ProcessStatusException;
import com.nie.tool.common.flowable.common.handler.BaseProcessInstanceStartHandler;
import com.nie.tool.common.flowable.common.manager.FlowableProcessManager;
import com.nie.tool.common.flowable.common.model.enums.FlowableProcessStatus;
import com.nie.tool.common.flowable.common.util.FlowableApiExecute;
import com.nie.tool.common.flowable.common.util.FlowableVariable;
import com.nie.tool.common.flowable.oa.config.OaConstEnum;
import com.nie.tool.common.flowable.oa.model.dto.*;
import com.nie.tool.common.flowable.oa.service.OaProcessService;
import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author njy
 * @since 2024/7/1 16:54
 */
@Service
@RequiredArgsConstructor
public class OaProcessServiceImpl implements OaProcessService {
    private final HistoryService         historyService;
    private final RepositoryService      repositoryService;
    private final FlowableProcessManager flowableProcessManager;
    private final BaseProcessInstanceStartHandler oaProcessInstanceStartHandler;

    @Override
    @ProcessCheck(throwException = true, checkStatus = true)
    public void startProcess(ApplyStartDTO applyStartDTO) throws ProcessNotFoundException, ProcessStatusException {
        FlowableVariable flowableVariable = applyStartDTO.buildFlowableVariable();
        oaProcessInstanceStartHandler.startProcessInstanceByKeyAndTenantId(applyStartDTO.getProcessKey(),
                applyStartDTO.getTenantId(), flowableVariable.getRawHashMap());
    }


    @Override
    @ProcessCheck(throwException = true, checkStatus = true, allowExecuteStatus = {FlowableProcessStatus.START, FlowableProcessStatus.STOP})
    public OaProcessDTO listUserStartProcess(OaBaseQueryDTO oaBaseQueryDTO) throws ProcessNotFoundException, ProcessStatusException {
        OaProcessDTO processDetailDTO = new OaProcessDTO();
        processDetailDTO.setFlowableProcessDetail(flowableProcessManager.getByProcessKeyAndTenantId(oaBaseQueryDTO.getProcessKey(), oaBaseQueryDTO.getTenantId()));
        ProcessDefinition processDefinition = FlowableApiExecute.supply(() -> repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(oaBaseQueryDTO.getProcessKey())
                .processDefinitionTenantId(oaBaseQueryDTO.getTenantId())
                .latestVersion()
                .singleResult(), oaBaseQueryDTO.getProcessKey(), oaBaseQueryDTO.getTenantId());
        List<HistoricProcessInstance> historicProcessInstances = FlowableApiExecute.supply(() -> historyService
                .createHistoricProcessInstanceQuery()
                .processDefinitionId(processDefinition.getId())
                .variableValueEquals(OaConstEnum.VAR_KEY_START_USER_ID.getValue(), oaBaseQueryDTO.getUserId())
                .orderByProcessInstanceStartTime()
                .desc()
                .list(), oaBaseQueryDTO.getProcessKey(), oaBaseQueryDTO.getTenantId());
        if (CollectionUtil.isNotEmpty(historicProcessInstances)) {
            List<OaProcessInstanceDTO> processInstances = historicProcessInstances.stream().map(e -> {
                OaProcessInstanceDTO instanceDTO = new OaProcessInstanceDTO();
                instanceDTO.setInstanceId(e.getId());
                instanceDTO.setProcessDefinitionId(e.getProcessDefinitionId());
                instanceDTO.setEndActivityId(e.getEndActivityId());
                instanceDTO.setDurationInMillis(e.getDurationInMillis());
                instanceDTO.setStartTime(e.getStartTime());
                instanceDTO.setEndTime(e.getEndTime());
                instanceDTO.setProcessStatus(null);

                List<HistoricTaskInstance> historicTaskInstances = FlowableApiExecute.supply(() -> historyService.createHistoricTaskInstanceQuery()
                        .processInstanceId(e.getId())
                        .orderByHistoricTaskInstanceEndTime()
                        .asc()
                        .list(), oaBaseQueryDTO.getProcessKey(), oaBaseQueryDTO.getTenantId());
                if (CollectionUtil.isNotEmpty(historicTaskInstances)) {
                    List<OaTaskDTO> oaTaskDTOS = historicTaskInstances.stream().map(t -> {
                        OaTaskDTO oaTaskDTO = new OaTaskDTO();
                        oaTaskDTO.setTaskId(t.getId());
                        oaTaskDTO.setTaskType(null);
                        oaTaskDTO.setProcessInstanceId(null);
                        oaTaskDTO.setProcessDefinitionId(null);
                        oaTaskDTO.setProcessKey(null);
                        oaTaskDTO.setTaskDefinitionKey(t.getTaskDefinitionKey());
                        oaTaskDTO.setTaskName(t.getName());
                        oaTaskDTO.setAssignee(t.getAssignee());
                        oaTaskDTO.setDurationInMillis(t.getDurationInMillis());
                        oaTaskDTO.setStartTime(t.getStartTime());
                        oaTaskDTO.setEndTime(t.getEndTime());

                        List<HistoricVariableInstance> variables = FlowableApiExecute.supply(() -> historyService.createHistoricVariableInstanceQuery()
                                .taskId(t.getId())
                                .list(), oaBaseQueryDTO.getProcessKey(), oaBaseQueryDTO.getTenantId());
                        if (CollectionUtil.isNotEmpty(variables)) {
                            oaTaskDTO.setVariables(variables.stream()
                                    .collect(Collectors.toMap(HistoricVariableInstance::getVariableName, HistoricVariableInstance::getValue)));
                        }
                        return oaTaskDTO;
                    }).collect(Collectors.toList());
                    instanceDTO.setTaskDetails(oaTaskDTOS);
                }
                return instanceDTO;
            }).collect(Collectors.toList());
            processDetailDTO.setProcessInstances(processInstances);
        }
        return processDetailDTO;
    }
}
