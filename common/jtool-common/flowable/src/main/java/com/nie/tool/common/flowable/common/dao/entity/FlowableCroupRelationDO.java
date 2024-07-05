package com.nie.tool.common.flowable.common.dao.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * @author njy
 * @since 2024/6/27 14:09
 */
@Data
@Table("flo_common_group_relation")
public class FlowableCroupRelationDO {
    /**
     * 分组key
     */
    @Column("group_key")
    private String  groupKey;
    /**
     * 用户身份id
     */
    @Column("identity_id")
    private String  identityId;
    /**
     * 租户id
     */
    @Column("tenant_id")
    private String  tenantId;
    /**
     * 排序
     */
    @Column("order_")
    private Integer order = 0;
}
