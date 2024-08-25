package com.nie.tool.common.core.util;

import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author njy
 * @since 2024/8/9 09:09
 */
public class HashCollisions {

    private static final char[] STRING_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();


    public static List<String> findCollisions(String val, int maxLength) {
        List<String> collisions = new ArrayList<>();
        int targetHashCode = val.hashCode();

        generateCombinations("", STRING_CHARS, maxLength, targetHashCode, collisions);

        return collisions;
    }

    private static void generateCombinations(String prefix, char[] chars, int maxLength, int targetHashCode, List<String> collisions) {
        if (prefix.length() > 0 && prefix.hashCode() == targetHashCode
                && !prefix.equals(collisions)) {
            collisions.add(prefix);
        }

        if (prefix.length() < maxLength) {
            for (char c : chars) {
                generateCombinations(prefix + c, chars, maxLength, targetHashCode, collisions);
            }
        }
    }

    public static void main(String[] args) {
//        String val = "test";
//        List<String> collisions = findCollisions(val, 5);
//
//        System.out.println("Input string: " + val);
//        System.out.println("Hash code: " + val.hashCode());
//        System.out.println("Collisions: " + collisions);

        List<String> list = Arrays.asList("tetU", "teu6", "tfTt", "tfUU", "tfV6", "tg5t", "tg6U", "tg76",
                "uFst", "uFtU","uFu6");
//        List<String> list = Arrays.asList("tetU", "teu6", "tfTt", "tfUU", "tfV6", "tg5t", "tg6U", "tg76", "uFst",
//                "uFtU", "uFu6", "uGTt", "uGUU", "uGV6", "uH5t", "uH76", "uH6U");
        checkHashCode("test", list);

        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), i + "");
        }
        // 8 16
        // 9 32
        // 10 64

        // 11 -> tree
        System.out.println(map.size());
        checkTreeNodeInHashMap(map);
        map.remove("uFu6");
        map.remove("uFtU");
        map.remove("uFst");
        map.remove("tg76");
        map.remove("tetU");
        map.remove("teu6");
        checkTreeNodeInHashMap(map);
    }

    public static void checkTreeNodeInHashMap2(HashMap<?, ?> map) {
        try {
            // 获取 HashMap 的 Class 对象
            VarHandle tableHandle = MethodHandles.privateLookupIn(HashMap.class, MethodHandles.lookup())
                    .findVarHandle(HashMap.class, "table", Object[].class);

            // 获取 table 字段的值
            Object[] table = (Object[]) tableHandle.get(map);

            if (table != null) {
                for (Object node : table) {
                    if (node != null) {
                        // 获取链表或树中的每一个节点
                        Class<?> nodeClass = node.getClass();

                        // 判断当前节点是否是 TreeNode 的实例
                        if ("java.util.HashMap$TreeNode".equals(nodeClass.getName())) {
                            System.out.println("Found an instance of TreeNode in the HashMap");
                        }
                    }
                }
            } else {
                System.out.println("The HashMap is empty or table is null.");
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void checkTreeNodeInHashMap1(HashMap<?, ?> map) {
        try {
            // 获取 Unsafe 实例
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsafeField.get(null);

            // 获取 HashMap 的 "table" 字段
            Field tableField = HashMap.class.getDeclaredField("table");
            tableField.setAccessible(true);
            // 使用 Unsafe 设置字段可访问
            unsafe.putObjectVolatile(map, unsafe.objectFieldOffset(tableField), tableField);

            Object[] table = (Object[]) tableField.get(map);

            if (table != null) {
                for (Object node : table) {
                    if (node != null) {
                        Class<?> nodeClass = node.getClass();
                        if ("java.util.HashMap$TreeNode".equals(nodeClass.getName())) {
                            System.out.println("Found an instance of TreeNode in the HashMap");
                        }
                    }
                }
            } else {
                System.out.println("The HashMap is empty or table is null.");
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void checkTreeNodeInHashMap(HashMap<?, ?> map) {
        try {

            // 获取 HashMap 的 Class 对象
            Class<?> hashMapClass = HashMap.class;

            // 通过反射获取 HashMap 的 "table" 字段
            Field tableField = hashMapClass.getDeclaredField("table");
            tableField.setAccessible(true);

            Field threshold = hashMapClass.getDeclaredField("threshold");
            threshold.setAccessible(true);
            System.out.println(threshold.get(map));


            // 获取 table 字段的值
            Object[] table = (Object[]) tableField.get(map);

            if (table != null) {
                for (Object node : table) {
                    if (node != null) {
                        // 获取链表或树中的每一个节点
                        Class<?> nodeClass = node.getClass();
                        // 判断当前节点是否是 TreeNode 的实例
                        if ("java.util.HashMap$TreeNode".equals(nodeClass.getName())) {
                            System.out.println("Found an instance of TreeNode in the HashMap");
                            return;
                        }
                    }
                }
            } else {
                System.out.println("The HashMap is empty or table is null.");
            }
            System.out.println("The HashMap is empty or table is null.");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void checkHashCode(String originalVal, List<String> compareVal) {
        int i = originalVal.hashCode();
        System.out.println(originalVal + ":  " + i);

        System.out.println("----");
        for (String s : compareVal) {
            int hashCode = s.hashCode();
            if (hashCode != i) {
                System.out.println(s + ":  " + hashCode);
            }
        }
        System.out.println("----");
    }


}
