package com.nie.tool.common.core.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author njy
 * @since 2022/9/20 08:58
 */
@Slf4j
@SuppressWarnings("unchecked")
public class JsonUtil {
    private JsonUtil() {
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        JavaTimeModule timeModule = new JavaTimeModule();
        // 设置LocalDateTime的序列化格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        OBJECT_MAPPER.registerModule(timeModule);
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    /**
     * 解析json字符串
     *
     * @param jsonStr jsonStr
     * @return com.fasterxml.jackson.databind.JsonNode
     * @author njy
     * @since 13:28 2022/11/18
     */
    public static JsonNode parserJsonStr(String jsonStr) {
        JsonNode jsonNode = null;
        try {
            jsonNode = getObjectMapper().readTree(jsonStr);
        } catch (JsonProcessingException e) {
            log.warn("parserJsonStr错误,jsonStr:{}", jsonStr);
        }
        return jsonNode;
    }

    /**
     * object to string
     *
     * @param obj obj
     * @return java.lang.String
     * @author njy
     * @since 09:04 2022/9/20
     */
    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("obj2String解析错误,obj:{}", obj);
            return null;
        }
    }

    /**
     * @param obj obj
     * @return java.lang.String
     * @author njy
     * @since 09:06 2022/9/20
     */
    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("obj2StringPretty解析错误,obj:{}", obj);
            return null;
        }
    }

    /**
     * @param str   str
     * @param clazz clazz
     * @return T
     * @author njy
     * @since 09:09 2022/9/20
     */
    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (str == null || str.length() == 0 || clazz == null) {
            return null;
        }
        T t = null;
        try {
            t = clazz.equals(String.class) ? (T) str : OBJECT_MAPPER.readValue(str, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> T string2Obj(String str, Class<?>... clazz) {
        if (str == null || str.length() == 0 || clazz == null || clazz.length < 1) {
            return null;
        }
        T t = null;
        JavaType type = (JavaType) getClass(clazz);
        try {
            t = OBJECT_MAPPER.readValue(str, type);
        } catch (IOException e) {
            log.error("string2Obj() called with exception => [str = {}], [clazz = {}]", str, clazz, e);
        }
        return t;
    }

    public static Type getClass(Class<?>... clazz) {
        TypeFactory typeFactory = OBJECT_MAPPER.getTypeFactory();
        JavaType type = typeFactory.constructType(clazz[clazz.length - 1]);
        if (clazz.length > 1) {
            for (int i = clazz.length - 2; i >= 0; i--) {
                type = typeFactory.constructParametricType(clazz[i], type);
            }
        }
        return type;
    }


    /**
     * 在字符串与集合对象转换时使用
     *
     * @param str           str
     * @param typeReference typeReference
     * @return T
     * @author njy
     * @since 09:10 2022/9/20
     */
    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (str == null || str.length() == 0 || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : OBJECT_MAPPER.readValue(str, typeReference));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 在字符串与集合对象转换时使用
     */
    public static <T> T string2Obj(String str, Class<?> collectionClazz, Class<?>... elementClazzes) {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClazz, elementClazzes);
        try {
            return OBJECT_MAPPER.readValue(str, javaType);
        } catch (IOException e) {
            return null;
        }
    }

}
