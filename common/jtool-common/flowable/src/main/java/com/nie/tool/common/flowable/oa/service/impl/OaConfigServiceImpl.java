package com.nie.tool.common.flowable.oa.service.impl;


import com.nie.tool.common.flowable.oa.config.OaConstEnum;
import com.nie.tool.common.flowable.oa.config.OaConstType;
import com.nie.tool.common.flowable.oa.service.OaConfigService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author njy
 * @since 2024/7/2 09:24
 */
@Service
public class OaConfigServiceImpl implements OaConfigService {
    @Override
    public Map<String, String> listSystemDefineVariables() {
        return Arrays.stream(OaConstEnum.values())
                .filter(value -> OaConstType.VARIABLE.equals(value.getType()))
                .collect(Collectors.toMap(OaConstEnum::getValue, OaConstEnum::getText));
    }
}
