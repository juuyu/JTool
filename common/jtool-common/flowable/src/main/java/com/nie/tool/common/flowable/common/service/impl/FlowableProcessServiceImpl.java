package com.nie.tool.common.flowable.common.service.impl;


import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.nie.tool.common.flowable.common.annotation.ProcessCheck;
import com.nie.tool.common.flowable.common.config.BaseFlowableConfigProperties;
import com.nie.tool.common.flowable.common.dao.entity.FlowableProcessDO;
import com.nie.tool.common.flowable.common.exception.ProcessNotFoundException;
import com.nie.tool.common.flowable.common.exception.ProcessStatusException;
import com.nie.tool.common.flowable.common.manager.FlowableProcessManager;
import com.nie.tool.common.flowable.common.model.dto.BaseProcessQueryDTO;
import com.nie.tool.common.flowable.common.model.enums.FlowableProcessStatus;
import com.nie.tool.common.flowable.common.service.FlowableProcessService;
import com.nie.tool.common.flowable.common.util.FlowableApiExecute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author njy
 * @since 2024/7/2 10:39
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FlowableProcessServiceImpl implements FlowableProcessService {
    private final FlowableProcessManager flowableProcessManager;
    private final RepositoryService            repositoryService;
    private final BaseFlowableConfigProperties baseFlowableConfigProperties;


    @Override
    @ProcessCheck(throwException = true, checkStatus = true,
            allowExecuteStatus = {FlowableProcessStatus.INIT, FlowableProcessStatus.STOP})
    public void loadProcess(BaseProcessQueryDTO processQueryDTO) throws ProcessNotFoundException, ProcessStatusException {
        log.info("loadProcess() called with parameters => [tenantId = {}], [processKey = {}]", processQueryDTO.getTenantId(), processQueryDTO.getProcessKey());

        FlowableProcessDO flowableProcessDO = flowableProcessManager.getByProcessKeyAndTenantId(processQueryDTO.getProcessKey(), processQueryDTO.getTenantId());
        if (FlowableProcessStatus.STOP.name().equals(flowableProcessDO.getStatus())) {
            flowableProcessManager.updateFlowableProcessStatus(processQueryDTO.getProcessKey(), processQueryDTO.getTenantId(), FlowableProcessStatus.START);
            return;
        }

        final String fileName = flowableProcessDO.getProcessKey() + baseFlowableConfigProperties.getBpmnFileSuffix();
        final String filePath = baseFlowableConfigProperties.getBpmnTmpPath() + fileName;
        // 下载bpmn文件
        HttpUtil.downloadFile(flowableProcessDO.getXmlFileUrl(), filePath);
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            FlowableApiExecute.run(() -> repositoryService.createDeployment()
                    .name(flowableProcessDO.getName())
                    .key(flowableProcessDO.getProcessKey())
                    .tenantId(flowableProcessDO.getTenantId())
                    .addInputStream(fileName, fileInputStream)
                    .deploy(), flowableProcessDO.getProcessKey(), flowableProcessDO.getTenantId());
            log.info("---部署成功---");
            flowableProcessManager.updateFlowableProcessStatus(processQueryDTO.getProcessKey(), processQueryDTO.getTenantId(), FlowableProcessStatus.START);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            FileUtil.del(filePath);
        }
    }

    @Override
    @ProcessCheck(throwException = true, checkStatus = true)
    public void stopProcess(BaseProcessQueryDTO processQueryDTO) {
        flowableProcessManager.updateFlowableProcessStatus(processQueryDTO.getProcessKey(), processQueryDTO.getTenantId(), FlowableProcessStatus.STOP);
    }

    @Override
    @ProcessCheck(throwException = true, checkStatus = true, allowExecuteStatus = {FlowableProcessStatus.INIT, FlowableProcessStatus.STOP})
    public void deleteProcess(BaseProcessQueryDTO processQueryDTO) throws ProcessNotFoundException, ProcessStatusException {
        log.info("loadProcess() called with parameters => [tenantId = {}], [processKey = {}]", processQueryDTO.getTenantId(), processQueryDTO.getProcessKey());

        FlowableProcessDO flowableProcessDO = flowableProcessManager.getByProcessKeyAndTenantId(processQueryDTO.getProcessKey(), processQueryDTO.getTenantId());
        // 先删除
        flowableProcessManager.deleteFlowableProcess(processQueryDTO.getProcessKey(), processQueryDTO.getTenantId());
        if (FlowableProcessStatus.INIT.name().equals(flowableProcessDO.getStatus())) {
            return;
        }
        // 删除flowable库里面的记录
        List<ProcessDefinition> processDefinitions = FlowableApiExecute.supply(() -> repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processQueryDTO.getProcessKey())
                .processDefinitionTenantId(processQueryDTO.getTenantId())
                .list(), processQueryDTO.getProcessKey(), processQueryDTO.getTenantId());
        for (ProcessDefinition processDefinition : processDefinitions) {
            FlowableApiExecute.run(() -> repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true));
        }
    }

    @Override
    @ProcessCheck(throwException = true)
    public InputStream getProcessDefinePngStream(BaseProcessQueryDTO processQueryDTO) throws ProcessNotFoundException {
        ProcessDefinition processDefinition = FlowableApiExecute.supply(() -> repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processQueryDTO.getProcessKey())
                .processDefinitionTenantId(processQueryDTO.getTenantId())
                .latestVersion()
                .singleResult(), processQueryDTO.getProcessKey(), processQueryDTO.getTenantId());
        if (processDefinition == null) {
            throw new RuntimeException("processDefinition is null");
        }
        return FlowableApiExecute.supply(() -> repositoryService.getProcessDiagram(processDefinition.getId()), processQueryDTO.getProcessKey(),
                processQueryDTO.getTenantId());
    }
}
