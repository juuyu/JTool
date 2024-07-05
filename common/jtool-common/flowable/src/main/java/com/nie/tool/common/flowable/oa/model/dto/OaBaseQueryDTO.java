package com.nie.tool.common.flowable.oa.model.dto;

import com.nie.tool.common.flowable.common.model.dto.BaseProcessQueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author njy
 * @since 2024/7/2 09:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OaBaseQueryDTO extends BaseProcessQueryDTO {
    /**
     * 用户身份id
     */
    private String userId;
}
