//package com.nie.tool.common.core.util;
//
//import cn.deti.common.core.consts.RequestHeaderConst;
//import cn.deti.common.core.enums.I18nEnum;
//import cn.deti.common.core.util.context.TenantUtil;
//import cn.deti.common.core.util.context.TraceUtil;
//import okhttp3.*;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author njy
// * @since 2024/8/19 17:18
// */
//public class HttpUtil {
//    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .build();
//
//    public static String get(String url, Map<String, String> headers) throws IOException {
//        Request.Builder request = new Request.Builder()
//                .url(url)
//                .get();
//
//        Map<String, String> commonHeaders = getCommonHeaders();
//        if (headers != null) {
//            commonHeaders.putAll(headers);
//        }
//        setHeaders(request, commonHeaders);
//
//        try (Response response = CLIENT.newCall(request.build()).execute()) {
//            if (!response.isSuccessful()) {
//                throw new IOException("Unexpected code " + response);
//            }
//
//            ResponseBody body = response.body();
//            return body.string();
//        }
//    }
//
//    public static String get(String url) throws IOException {
//        return get(url, null);
//    }
//
//    // POST 请求（Form 表单提交）
//    public static String post(String url, Map<String, String> params) throws IOException {
//        FormBody.Builder formBuilder = new FormBody.Builder();
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            formBuilder.add(entry.getKey(), entry.getValue());
//        }
//
//        RequestBody requestBody = formBuilder.build();
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//
//        try (Response response = CLIENT.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            return response.body().string();
//        }
//    }
//
//    // POST 请求（提交 JSON 数据）
//    public static String postJson(String url, String json) throws IOException {
//        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
//
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//
//        try (Response response = CLIENT.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            return response.body().string();
//        }
//    }
//
//
//    public static Map<String, String> getCommonHeaders() {
//        Map<String, String> headers = new HashMap<>();
//        if (StringUtil.isNotEmpty(TenantUtil.getTenantId())) {
//            headers.put(RequestHeaderConst.TENANT_ID, TenantUtil.getTenantId());
//        }
//        if (StringUtil.isEmpty(TraceUtil.getTraceId())) {
//            TraceUtil.initTraceId();
//        }
//        headers.put(RequestHeaderConst.TRACE_ID, TraceUtil.getTraceId());
//        headers.put(RequestHeaderConst.TIMESTAMP, System.currentTimeMillis() + "");
//        headers.put(RequestHeaderConst.LANGUAGE, I18nEnum.CN.getHeaderName());
//        return headers;
//    }
//
//    private static void setHeaders(Request.Builder requestBuilder, Map<String, String> headers) {
//        if (headers != null) {
//            headers.forEach(requestBuilder::addHeader);
//        }
//    }
//
//}
