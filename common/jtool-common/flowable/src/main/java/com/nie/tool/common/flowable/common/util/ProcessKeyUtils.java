package com.nie.tool.common.flowable.common.util;


import com.nie.tool.common.flowable.common.config.ProcessDeploymentCategory;

/**
 * @author njy
 * @since 2024/6/27 15:08
 */
public class ProcessKeyUtils {
    private static final String PROCESS_KEY_SPLIT = "#";

    public static String generateProcessKey(ProcessDeploymentCategory category, String key) {
        return category.getProcessKeyPrefix() + PROCESS_KEY_SPLIT + key;
    }

    public static ProcessDeploymentCategory getCategoryProcessKey(String key) {
        String[] split = key.split(PROCESS_KEY_SPLIT);
        return ProcessDeploymentCategory.parseByPrefix(split[0]);
    }
}

