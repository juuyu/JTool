//package com.nie.tool.common.core.util;
//
//import cn.deti.common.core.annotation.I18nMessage;
//import cn.deti.common.core.enums.I18nEnum;
//import cn.deti.common.core.util.context.I18nContextUtil;
//import cn.hutool.core.util.ArrayUtil;
//import cn.hutool.core.util.ReflectUtil;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
//import org.springframework.core.type.classreading.MetadataReader;
//import org.springframework.core.type.classreading.MetadataReaderFactory;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author njy
// * @since 2024/8/22 10:18
// */
//public class I18nMessageUtil {
//    private static final Map<String, Map<String, String>> i18nMessageMap = new HashMap<>(16);
//
//    private static boolean LOADED;
//
//    static {
//        LOADED = false;
//        // 初始化国际化信息存储
//        for (I18nEnum i18nEnum : I18nEnum.values()) {
//            i18nMessageMap.put(i18nEnum.getHeaderName(), new HashMap<>(2048));
//        }
//    }
//
//
//    public synchronized static void loadMessages(@NotNull String pattern) throws IOException, ClassNotFoundException {
//        if (!LOADED) {
//            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//            org.springframework.core.io.Resource[] resources = resourcePatternResolver.getResources(pattern);
//            MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
//            for (org.springframework.core.io.Resource resource : resources) {
//                MetadataReader reader = metadataReaderFactory.getMetadataReader(resource);
//                Class<?> clazz = Class.forName(reader.getClassMetadata().getClassName());
//                // 判断是否有指定主解
//                I18nMessage i18nMessage = clazz.getAnnotation(I18nMessage.class);
//                if (i18nMessage == null) {
//                    continue;
//                }
//                String prefix = i18nMessage.prefix();
//                Field[] fields = ReflectUtil.getFields(clazz);
//                for (Field field : fields) {
//                    String fieldValue = (String) ReflectUtil.getFieldValue(null, field);
//                    i18nMessageMap.get(i18nMessage.value().getHeaderName())
//                            .put(prefix + field.getName(), fieldValue);
//                }
//            }
//            LOADED = true;
//        }
//    }
//
//    /**
//     * 获取最后输出的信息
//     *
//     * @param i18n    i18n
//     * @param message message
//     * @param params  params
//     * @return java.lang.String
//     */
//    public static String getFinalMessage(boolean i18n, String message, Object... params) {
//        String finalMessage = i18n ? warpMessage(message) : message;
//        if (ArrayUtil.isEmpty(params)) {
//            return finalMessage;
//        }
//        return StringUtil.format(finalMessage, params);
//    }
//
//    /**
//     * 处理国际化信息包装返回
//     *
//     * @param messageKey messageKey
//     * @return java.lang.String
//     */
//    public static String warpMessage(String messageKey) {
//        if (!LOADED) {
//            return messageKey;
//        }
//        return i18nMessageMap.get(I18nContextUtil.getLang())
//                .getOrDefault(messageKey, messageKey);
//    }
//}
