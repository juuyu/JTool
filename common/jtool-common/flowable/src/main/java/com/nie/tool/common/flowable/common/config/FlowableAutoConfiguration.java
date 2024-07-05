package com.nie.tool.common.flowable.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author njy
 * @since 2024/7/3 17:36
 */
@Configuration
@MapperScan("com.nie.tool.common.flowable.common.dao.mapper")
public class FlowableAutoConfiguration {

}
