package com.nie.tool.common.cache.service;

/**
 * @author njy
 * @since 2024/6/28 14:21
 */
public interface JCacheService {

    void put(String key, Object value);

    <T> T get(String key);

}
