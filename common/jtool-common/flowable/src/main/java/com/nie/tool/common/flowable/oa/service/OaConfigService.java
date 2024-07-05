package com.nie.tool.common.flowable.oa.service;

import java.util.Map;

/**
 * @author njy
 * @since 2024/7/2 09:24
 */
public interface OaConfigService {

    /**
     * 查询系统定义的变量key
     *
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> listSystemDefineVariables();

}
