//package com.nie.tool.common.core.excepetion;
//
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.Arrays;
//
///**
// * @author njy
// * @since 2024/8/15 13:05
// */
//@Getter
//@Setter
//@EqualsAndHashCode(callSuper = true)
//public class BizException extends RuntimeException {
//
//
//    private BizException() {
//    }
//
//    /**
//     * 错误码
//     */
//    private Integer  code;
//    /**
//     * 错误提示
//     */
//    private String   message;
//    /**
//     * 是否国际化处理
//     */
//    private boolean  i18n;
//    /**
//     * 参数
//     */
//    private Object[] params;
//
//
//    public Result<Void> toResult() {
//        return Result.fail(code, message, i18n, params);
//    }
//
//
//    private static BizException restBusinessException(Integer code, String message, boolean i18n, Object[] params) {
//        BizException exception = new BizException();
//        exception.setCode(code);
//        exception.setMessage(message);
//        exception.setI18n(i18n);
//        exception.setParams(params);
//        return exception;
//    }
//
//    public static BizException build(String message) {
//        return restBusinessException(HttpResultCode.INTERNAL_SERVER_ERROR.getCode(), message, true, null);
//    }
//
//    public static BizException build(String message, Object... params) {
//        return restBusinessException(HttpResultCode.INTERNAL_SERVER_ERROR.getCode(), message, true, params);
//    }
//
//    public static BizException build(Integer code, String message) {
//        return restBusinessException(code, message, true, null);
//    }
//
//    public static BizException build(Integer code, String message, Object... params) {
//        return restBusinessException(code, message, true, params);
//    }
//
//    public static BizException build(String message, boolean i18n) {
//        return restBusinessException(HttpResultCode.INTERNAL_SERVER_ERROR.getCode(), message, i18n, null);
//    }
//
//    public static BizException build(String message, boolean i18n, Object... params) {
//        return restBusinessException(HttpResultCode.INTERNAL_SERVER_ERROR.getCode(), message, i18n, params);
//    }
//
//    public static BizException build(Integer code, boolean i18n, String message) {
//        return restBusinessException(code, message, i18n, null);
//    }
//
//    public static BizException build(Integer code, String message, boolean i18n, Object... params) {
//        return restBusinessException(code, message, i18n, params);
//    }
//
//    @Override
//    public String toString() {
//        return "BusinessException{" +
//                "trace=" + TraceUtil.getTraceId() +
//                ", code=" + code +
//                ", message='" + message + '\'' +
//                ", i18n=" + i18n +
//                ", params=" + Arrays.toString(params) +
//                '}' + " ExceptionDetail=" +
//                I18nMessageUtil.getFinalMessage(i18n, message, params);
//    }
//}
