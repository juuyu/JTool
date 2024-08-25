package com.nie.tool.common.core.util;


import com.nie.tool.common.core.util.execute.ExecuteUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author njy
 * @since 2024/6/24 16:12
 */
public abstract class StreamUtil {

    /**
     * warp(包装list, 防止操作list出现npe或者返回的是Collections.EMPTY_LIST无法操作)
     *
     * @param list list
     * @return java.util.List<E>
     */
    public static <E> List<E> warp(List<E> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        return list;
    }

    /**
     * stream
     *
     * @param list list
     * @return java.util.stream.Stream<E>
     */
    public static <E> Stream<E> stream(List<E> list) {
        return stream(list, true);
    }

    /**
     * stream
     *
     * @param list    list
     * @param nonNull nonNull
     * @return java.util.stream.Stream<E>
     */
    public static <E> Stream<E> stream(List<E> list, boolean nonNull) {
        Stream<E> listStream = warp(list).stream();
        return nonNull ? listStream.filter(Objects::nonNull) : listStream;
    }

    /**
     * filterNull
     *
     * @param originalList originalList
     * @return java.util.List<T>
     */
    public static <T> List<T> filterNull(List<T> originalList) {
        return stream(originalList, true).collect(Collectors.toList());
    }

    /**
     * map
     *
     * @param originalList originalList
     * @param transformer  transformer
     * @return java.util.List<R>
     */
    public static <T, R> List<R> map(List<T> originalList, Function<T, R> transformer) {
        return map(originalList, transformer, true);
    }

    /**
     * map
     *
     * @param originalList originalList
     * @param transformer  transformer
     * @param nonNull      nonNull
     * @return java.util.List<R>
     */
    public static <T, R> List<R> map(List<T> originalList, Function<T, R> transformer, boolean nonNull) {
        Stream<R> rStream = stream(originalList, true)
                .map(transformer);
        return nonNull ? rStream.filter(Objects::nonNull).collect(Collectors.toList()) :
                rStream.collect(Collectors.toList());
    }

    /**
     * mapParallel
     *
     * @param originalList originalList
     * @param transformer  transformer
     * @return java.util.List<R>
     */
    public static <T, R> List<R> mapParallel(List<T> originalList, Function<T, R> transformer) {
        return stream(originalList, true)
                .map(e -> ExecuteUtil.supplyAsync(() -> transformer.apply(e), null))
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * filter
     *
     * @param originalList originalList
     * @param predicate    predicate
     * @return java.util.List<T>
     */
    public static <T> List<T> filter(List<T> originalList, Predicate<? super T> predicate) {
        return stream(originalList, true)
                .filter(predicate)
                .collect(Collectors.toList());
    }

}
