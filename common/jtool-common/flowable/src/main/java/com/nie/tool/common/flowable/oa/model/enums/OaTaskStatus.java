package com.nie.tool.common.flowable.oa.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author njy
 * @since 2024/7/2 15:32
 */
@Getter
@AllArgsConstructor
public enum OaTaskStatus {

    PASS("通过", true),
    REJECT("驳回", true),
    PENDING("等待", false),
    RE_ASSIGNED("已转派", true);

    private final String  text;
    private final boolean finish;

}
