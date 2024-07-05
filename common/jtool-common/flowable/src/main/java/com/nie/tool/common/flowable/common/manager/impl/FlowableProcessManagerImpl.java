package com.nie.tool.common.flowable.common.manager.impl;

import com.nie.tool.common.flowable.common.dao.entity.FlowableProcessDO;
import com.nie.tool.common.flowable.common.dao.mapper.FlowableProcessMapper;
import com.nie.tool.common.flowable.common.manager.FlowableProcessManager;
import com.nie.tool.common.flowable.common.model.enums.FlowableProcessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author njy
 * @since 2024/7/3 09:54
 */
@Service
@RequiredArgsConstructor
public class FlowableProcessManagerImpl implements FlowableProcessManager {
    private final FlowableProcessMapper flowableProcessMapper;

    @Override
    public FlowableProcessDO getByProcessKeyAndTenantId(String processKey, String tenantId) {
//        return flowableProcessMapper.selectOne(new LambdaQueryWrapper<FlowableProcessDO>()
//                .eq(FlowableProcessDO::getTenantId, tenantId)
//                .eq(FlowableProcessDO::getProcessKey, processKey));
        // TODO replace mybatis-flex
        return null;
    }

    @Override
    public void updateFlowableProcessStatus(String processKey, String tenantId, FlowableProcessStatus status) {
        FlowableProcessDO processDO = new FlowableProcessDO();
        processDO.setStatus(status.name());
        processDO.setUpdateTime(new Date());
        // TODO replace mybatis-flex
//        flowableProcessMapper.update(processDO, new LambdaQueryWrapper<FlowableProcessDO>()
//                .eq(FlowableProcessDO::getProcessKey, processKey)
//                .eq(FlowableProcessDO::getTenantId, tenantId));
    }

    @Override
    public void saveFlowableProcess(FlowableProcessDO flowableProcessDO) {
        FlowableProcessDO processDO = getByProcessKeyAndTenantId(flowableProcessDO.getProcessKey(), flowableProcessDO.getTenantId());
        if (processDO != null) {
            throw new RuntimeException("该processKey[" + flowableProcessDO.getProcessKey() + "]已经被使用");
        }
        flowableProcessDO.setCreateTime(new Date());
        flowableProcessMapper.insert(flowableProcessDO);
    }

    @Override
    public void deleteFlowableProcess(String processKey, String tenantId) {
        // TODO replace mybatis-flex
//        flowableProcessMapper.delete(new LambdaQueryWrapper<FlowableProcessDO>()
//                .eq(FlowableProcessDO::getTenantId, tenantId)
//                .eq(FlowableProcessDO::getProcessKey, processKey));
    }


    @Override
    public List<FlowableProcessDO> listFlowableProcess(String tenantId) {
//        return flowableProcessMapper.selectList(new LambdaQueryWrapper<FlowableProcessDO>()
//                .eq(FlowableProcessDO::getTenantId, tenantId));
        // TODO replace mybatis-flex
        return null;
    }
}
