package com.nie.tool.common.web.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * @author njy
 * @since 2023/2/2 09:24
 */
public class R<T> implements Serializable {
    private R() {
    }

    public static <T> R<T> restResult(boolean success, String code, String tip, T data, Map<String, Object> extInfo) {
        R<T> r = new R<>();
        r.setSuccess(success);
        r.setCode(code);
        r.setTip(tip);
        r.setData(data);
        r.setExtInfo(extInfo);
        return r;
    }

    private boolean             success;
    private String              code;
    private String              tip;
    private T                   data;
    private Map<String, Object> extInfo;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, Object> extInfo) {
        this.extInfo = extInfo;
    }

    public static <T> R<T> ok() {
        return restResult(true, "SUCCESS", null, null, null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(true, "SUCCESS", null, data, null);
    }

    public static <T> R<T> ok(String code, String tip) {
        return restResult(true, code, tip, null, null);
    }

    public static <T> R<T> ok(String code, String tip, T data) {
        return restResult(true, code, tip, data, null);
    }

    public static <T> R<T> ok(String code, String tip, T data, Map<String, Object> extInfo) {
        return restResult(true, code, tip, data, extInfo);
    }

    public static <T> R<T> fail() {
        return restResult(false, "FAIL", null, null, null);
    }

    public static <T> R<T> fail(T data) {
        return restResult(false, "FAIL", null, data, null);
    }

    public static <T> R<T> fail(String code, String tip) {
        return restResult(false, code, tip, null, null);
    }

    public static <T> R<T> fail(String code, String tip, T data) {
        return restResult(false, code, tip, data, null);
    }

    public static <T> R<T> fail(String code, String tip, T data, Map<String, Object> extInfo) {
        return restResult(false, code, tip, data, extInfo);
    }

}
