//package com.nie.tool.common.core.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import java.io.Serializable;
//
///**
// * @author njy
// * @since 2024/8/15 10:55
// */
//@Getter
//@Setter
//public class Result<T> implements Serializable {
//
//    private Result() {
//    }
//
//    private Integer code;
//    private String  message;
//    private T       data;
//    private String  traceId;
//
//    @JsonIgnore
//    private transient boolean  i18n;
//    @JsonIgnore
//    private transient Object[] params;
//
//    public static <T> Result<T> restResult(Integer code, String message, T data, boolean i18n, Object... params) {
//        Result<T> r = new Result<>();
//        r.setCode(code);
//        r.setData(data);
//        r.setTraceId(TraceUtil.getTraceId());
//        r.setI18n(i18n);
//        r.setParams(params);
//        r.setMessage(I18nMessageUtil.getFinalMessage(i18n, message, params));
//        return r;
//    }
//
//    public static <T> Result<T> ok() {
//        return restResult(HttpResultCode.SUCCESS.getCode(), HttpResultCode.SUCCESS.getMessage(), null, true);
//    }
//
//    public static <T> Result<T> ok(T data) {
//        return restResult(HttpResultCode.SUCCESS.getCode(), HttpResultCode.SUCCESS.getMessage(), data, true);
//    }
//
//
//    public static <T> Result<T> fail() {
//        return restResult(HttpResultCode.INTERNAL_SERVER_ERROR.getCode(), HttpResultCode.INTERNAL_SERVER_ERROR.getMessage(), null, true);
//    }
//
//    public static <T> Result<T> fail(T data) {
//        return restResult(HttpResultCode.INTERNAL_SERVER_ERROR.getCode(), HttpResultCode.INTERNAL_SERVER_ERROR.getMessage(), data, true);
//    }
//
//    public static <T> Result<T> fail(Integer code, String message, boolean i18n, Object... params) {
//        return restResult(code, message, null, i18n, params);
//    }
//
//    public static <T> Result<T> fail(Integer code, String message, T data, boolean i18n, Object... params) {
//        return restResult(code, message, data, i18n, params);
//    }
//
//
//    public static <T> Result<T> fail(HttpResultCode httpResultCode) {
//        return restResult(httpResultCode.getCode(), httpResultCode.getMessage(), null, true);
//    }
//
//    @Override
//    public String toString() {
//        return JsonUtil.obj2String(this);
//    }
//}
