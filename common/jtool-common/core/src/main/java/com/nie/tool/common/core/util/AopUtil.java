package com.nie.tool.common.core.util;

import org.aspectj.lang.JoinPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @author njy
 * @since 2024/8/19 09:52
 */
public class AopUtil {


    /**
     * 获取方法完全限定名称
     *
     * @param joinPoint joinPoint
     * @return java.lang.String
     */
    public static String getMethodFullyQualifiedName(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        return className + "." + methodName;
    }

    /**
     * getArgsOfType
     *
     * @param joinPoint joinPoint
     * @param clazz     clazz
     * @return java.util.List<T>
     */
    public static <T> List<T> getArgsOfType(JoinPoint joinPoint, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (clazz.isInstance(arg)) {
                result.add(clazz.cast(arg));
            }
        }
        return result;
    }

    /**
     * getArgOfType
     *
     * @param joinPoint joinPoint
     * @param clazz     clazz
     * @return T
     */
    public static <T> T getArgOfType(JoinPoint joinPoint, Class<T> clazz) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (clazz.isInstance(arg)) {
                return clazz.cast(arg);
            }
        }
        return null;
    }

}
