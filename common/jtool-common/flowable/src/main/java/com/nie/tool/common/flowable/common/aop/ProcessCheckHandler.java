package com.nie.tool.common.flowable.common.aop;


import com.nie.tool.common.flowable.common.annotation.ProcessCheck;
import com.nie.tool.common.flowable.common.dao.entity.FlowableProcessDO;
import com.nie.tool.common.flowable.common.exception.ProcessNotFoundException;
import com.nie.tool.common.flowable.common.exception.ProcessStatusException;
import com.nie.tool.common.flowable.common.manager.FlowableProcessManager;
import com.nie.tool.common.flowable.common.model.dto.BaseProcessQueryDTO;
import com.nie.tool.common.flowable.common.model.enums.FlowableProcessStatus;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author njy
 * @since 2024/7/3 11:24
 */
@Aspect
@Component
@Slf4j
@EnableAspectJAutoProxy
public class ProcessCheckHandler {

    @Resource
    private FlowableProcessManager flowableProcessManager;

    @Around("@annotation(processCheck)")
    public Object checkProcess(ProceedingJoinPoint joinPoint, ProcessCheck processCheck) throws Throwable {
        log.info("checkProcess() called with parameters => [joinPoint = {}], [processCheck = {}]", joinPoint, processCheck);

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BaseProcessQueryDTO) {
                BaseProcessQueryDTO dto = (BaseProcessQueryDTO) arg;
                String processKey = dto.getProcessKey();
                String tenantId = dto.getTenantId();
                log.info("checkProcess() called with parameters => [processKey = {}, tenantId = {}]",
                        processKey, tenantId);
                FlowableProcessDO process = flowableProcessManager.getByProcessKeyAndTenantId(processKey, tenantId);
                if (process == null) {
                    if (processCheck.throwException()) {
                        throw new ProcessNotFoundException(processKey, tenantId);
                    } else {
                        return null;
                    }
                }
                if (processCheck.checkStatus()) {
                    FlowableProcessStatus[] allowExecuteStatus = processCheck.allowExecuteStatus();
                    Set<String> allowStatus = Arrays.stream(allowExecuteStatus)
                            .map(FlowableProcessStatus::name)
                            .collect(Collectors.toSet());
                    if (!allowStatus.contains(process.getStatus())) {
                        if (processCheck.throwException()) {
                            throw new ProcessStatusException(processKey, tenantId, process.getStatus(), allowExecuteStatus);
                        } else {
                            return null;
                        }
                    }
                }
                break;
            }
        }
        return joinPoint.proceed();
    }


}
