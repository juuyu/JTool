package com.nie.tool.common.flowable.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author njy
 * @since 2024/7/4 14:15
 */
@Data
@Component
@ConfigurationProperties(prefix = "deti.flowable")
public class BaseFlowableConfigProperties {
    /**
     * bpmn文件初始化地址
     */
    private String bpmnTmpPath    = "/tmp/";
    /**
     * bpmn xml文件后缀
     */
    private String bpmnFileSuffix = ".bpmn20.xml";
}
