package com.nie.tool.common.flowable.common.manager;


import com.nie.tool.common.flowable.common.dao.entity.FlowableProcessDO;
import com.nie.tool.common.flowable.common.model.enums.FlowableProcessStatus;

import java.util.List;

/**
 * @author njy
 * @since 2024/7/1 15:52
 */
public interface FlowableProcessManager {


    /**
     * getByProcessKeyAndTenantId
     *
     * @param processKey processKey
     * @param tenantId   tenantId
     * @return cn.deti.flowable.common.dao.entity.FlowableProcessDO
     */
    FlowableProcessDO getByProcessKeyAndTenantId(String processKey, String tenantId);

    /**
     * updateFlowableProcessStatus
     *
     * @param processKey processKey
     * @param tenantId   tenantId
     * @param status     status
     */
    void updateFlowableProcessStatus(String processKey, String tenantId, FlowableProcessStatus status);

    /**
     * saveFlowableProcess
     *
     * @param flowableProcessDO flowableProcessDO
     */
    void saveFlowableProcess(FlowableProcessDO flowableProcessDO);

    /**
     * deleteFlowableProcess
     *
     * @param processKey processKey
     * @param tenantId   tenantId
     */
    void deleteFlowableProcess(String processKey, String tenantId);


    /**
     * listFlowableProcess
     *
     * @param tenantId tenantId
     * @return java.util.List<cn.deti.flowable.common.dao.entity.FlowableProcessDO>
     */
    List<FlowableProcessDO> listFlowableProcess(String tenantId);

}
