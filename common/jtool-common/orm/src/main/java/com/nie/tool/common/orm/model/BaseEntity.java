package com.nie.tool.common.orm.model;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Comparator;
import java.util.Date;

/**
 * @author njy
 * @since 2024/7/16 15:51
 */
@Data
@Accessors(chain = true)
public abstract class BaseEntity implements Comparator<BaseEntity> {
    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
    @Column("id_")
    private Long id;
    /**
     * 创建人id
     */
    @Column(value = "creator_id")
    private Long creatorId;
    /**
     * 创建时间
     */
    @Column(value = "create_time", onInsertValue = "now()")
    private Date createTime;
    /**
     * 更新人id
     */
    @Column("create_time")
    private Long updaterId;
    /**
     * 更新时间
     */
    @Column(value = "create_time", onUpdateValue = "now()")
    private Date updateTime;
    /**
     * 逻辑删除标志, 未删除: null, 已删除: 主键值
     */
    @Column(value = "deleted_flag", isLogicDelete = true)
    // PrimaryKeyLogicDeleteProcessor LogicDeleteManager.setProcessor(new DateTimeLogicDeleteProcessor());
    private Long deletedFlag;


    private Long orderCode;

    @Override
    public int compare(BaseEntity o1, BaseEntity o2) {
        return 0;
    }
}
