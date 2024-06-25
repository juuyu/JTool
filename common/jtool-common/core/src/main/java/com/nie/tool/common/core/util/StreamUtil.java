package com.nie.tool.common.core.util;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author njy
 * @since 2024/6/24 16:12
 */
public class StreamUtil {

    public static <T, R> List<R> toList(List<T> originalList, Function<T, R> transformer) {
        if (originalList == null){
            return new ArrayList<>();
        }
        return originalList.stream()
                .map(transformer)
                .collect(Collectors.toList());
    }

}
