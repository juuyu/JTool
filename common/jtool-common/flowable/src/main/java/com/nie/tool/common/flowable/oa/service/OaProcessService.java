package com.nie.tool.common.flowable.oa.service;


import com.nie.tool.common.flowable.common.exception.ProcessNotFoundException;
import com.nie.tool.common.flowable.common.exception.ProcessStatusException;
import com.nie.tool.common.flowable.oa.model.dto.ApplyStartDTO;
import com.nie.tool.common.flowable.oa.model.dto.OaBaseQueryDTO;
import com.nie.tool.common.flowable.oa.model.dto.OaProcessDTO;

/**
 * @author njy
 * @since 2024/7/1 16:54
 */
public interface OaProcessService {


    /**
     * 启动流程
     *
     * @param applyStartDTO applyStartDTO
     */
    void startProcess(ApplyStartDTO applyStartDTO) throws ProcessNotFoundException, ProcessStatusException;

    /**
     * 查询用户提交的审批流
     *
     * @param oaBaseQueryDTO oaBaseQueryDTO
     * @return cn.deti.flowable.oa.model.dto.OaProcessDTO
     */
    OaProcessDTO listUserStartProcess(OaBaseQueryDTO oaBaseQueryDTO) throws ProcessNotFoundException, ProcessStatusException;
}
