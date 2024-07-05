package com.nie.tool.common.flowable.oa.model.enums;


import com.nie.tool.common.flowable.common.util.FlowableVariable;
import com.nie.tool.common.flowable.oa.config.OaConstEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author njy
 * @since 2024/7/1 17:52
 */
@AllArgsConstructor
@Getter
public enum ApprovalResult {

    PASS(1, "通过"),
    REJECT(0, "驳回"),

    OTHER(-1, "其他");

    private final int    code;
    private final String text;


    public static ApprovalResult parseByVariable(FlowableVariable flowableVariable) {
        Integer value = (Integer) flowableVariable.getValue(OaConstEnum.VAR_KEY_APPROVE_RESULT.getValue());
        return parseByCode(value);
    }

    public static ApprovalResult parseByCode(int code) {
        if (code > 0) {
            return ApprovalResult.PASS;
        } else if (code == 0) {
            return ApprovalResult.REJECT;
        } else {
            return ApprovalResult.OTHER;
        }
    }

}
