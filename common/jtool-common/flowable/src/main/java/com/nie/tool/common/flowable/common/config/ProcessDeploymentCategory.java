package com.nie.tool.common.flowable.common.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author njy
 * @since 2024/6/27 15:05
 */
@Getter
@AllArgsConstructor
public enum ProcessDeploymentCategory {
    OA("OA审批", "PROCESS_OA");
    private final String text;
    private final String processKeyPrefix;


    public static ProcessDeploymentCategory parseByPrefix(String prefix){
        for (ProcessDeploymentCategory category : values()) {
            if (category.getProcessKeyPrefix().equals(prefix)){
                return category;
            }
        }
        return null;
    }
}
