package com.nie.tool.common.flowable.oa.service;



import com.nie.tool.common.flowable.common.exception.ProcessNotFoundException;
import com.nie.tool.common.flowable.common.exception.ProcessStatusException;
import com.nie.tool.common.flowable.common.util.FlowableVariable;
import com.nie.tool.common.flowable.oa.model.dto.OaBaseQueryDTO;
import com.nie.tool.common.flowable.oa.model.dto.OaTaskDTO;

import java.util.List;

/**
 * @author njy
 * @since 2024/7/1 17:31
 */
public interface OaTaskService {


    /**
     * 查询用户待审批任务
     *
     * @param oaBaseQueryDTO oaBaseQueryDTO
     * @return java.util.List<cn.deti.flowable.oa.model.dto.OaTaskDTO>
     */
    List<OaTaskDTO> listUserNeedApproveTask(OaBaseQueryDTO oaBaseQueryDTO) throws ProcessNotFoundException, ProcessStatusException;


    /**
     * 查询用户历史审批任务
     *
     * @param oaBaseQueryDTO oaBaseQueryDTO
     * @return java.util.List<cn.deti.flowable.oa.model.dto.OaTaskDTO>
     */
    List<OaTaskDTO> listUserApprovedTask(OaBaseQueryDTO oaBaseQueryDTO);

    /**
     * 通过
     *
     * @param taskId   taskId
     * @param variable variable
     */
    void pass(String taskId, FlowableVariable variable);

    /**
     * 拒绝
     *
     * @param taskId   taskId
     * @param variable variable
     */
    void reject(String taskId, FlowableVariable variable);

}
