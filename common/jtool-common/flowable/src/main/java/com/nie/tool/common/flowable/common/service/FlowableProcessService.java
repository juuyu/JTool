package com.nie.tool.common.flowable.common.service;



import com.nie.tool.common.flowable.common.exception.ProcessNotFoundException;
import com.nie.tool.common.flowable.common.exception.ProcessStatusException;
import com.nie.tool.common.flowable.common.model.dto.BaseProcessQueryDTO;

import java.io.InputStream;

/**
 * @author njy
 * @since 2024/7/2 10:39
 */
public interface FlowableProcessService {

    /**
     * 部署流程
     *
     * @param processQueryDTO processQueryDTO   tenantId
     */
    void loadProcess(BaseProcessQueryDTO processQueryDTO) throws ProcessNotFoundException, ProcessStatusException;


    /**
     * 暂停流程（已发起的流程不受影响, 仅无法再次新发起该流程）
     *
     * @param processQueryDTO processQueryDTO
     */
    void stopProcess(BaseProcessQueryDTO processQueryDTO);

    /**
     * 删除流程（彻底删除流程所有信息包括历史流程记录, 只能删除状态为初始化和暂停的流程）
     *
     * @param processQueryDTO processQueryDTO
     */
    void deleteProcess(BaseProcessQueryDTO processQueryDTO) throws ProcessNotFoundException, ProcessStatusException;

    /**
     * getProcessDefinePngStream
     *
     * @param processQueryDTO processQueryDTO   tenantId
     * @return java.io.InputStream
     */
    InputStream getProcessDefinePngStream(BaseProcessQueryDTO processQueryDTO) throws ProcessNotFoundException;

}
