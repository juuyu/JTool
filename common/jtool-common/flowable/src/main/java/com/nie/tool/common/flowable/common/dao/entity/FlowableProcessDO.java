package com.nie.tool.common.flowable.common.dao.entity;


import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.util.Date;

/**
 * @author njy
 * @since 2024/6/27 11:44
 */
@Data
@Table("flo_common_process")
public class FlowableProcessDO {

    /**
     * 流程定义key
     */
    @Column("process_key")
    private String processKey;
    /**
     * 流程分类
     */
    @Column("type_")
    private String type;
    /**
     * 流程状态
     */
    @Column("status_")
    private String status;
    /**
     * 流程名称
     */
    @Column("name_")
    private String name;
    /**
     * 流程描述
     */
    @Column("description_")
    private String description;
    /**
     * bpmn文件地址
     */
    @Column("xml_file_url")
    private String xmlFileUrl;
    /**
     * 流程创建时间
     */
    @Column("create_time")
    private Date   createTime;
    /**
     * 流程修改时间
     */
    @Column("update_time")
    private Date   updateTime;
    /**
     * 租户id
     */
    @Column("tenant_id")
    private String tenantId;
}
