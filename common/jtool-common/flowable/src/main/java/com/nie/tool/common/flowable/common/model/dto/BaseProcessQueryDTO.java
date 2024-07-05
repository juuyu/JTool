package com.nie.tool.common.flowable.common.model.dto;

import lombok.Data;

/**
 * @author njy
 * @since 2024/7/3 11:32
 */
@Data
public class BaseProcessQueryDTO {
    /**
     * 流程key
     */
    private String processKey;
    /**
     * 租户id
     */
    private String tenantId;
}
