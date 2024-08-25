package com.nie.tool.common.core.util;

/**
 * @author njy
 * @since 2024/7/16 11:34
 */
public class Base62Util {

    private Base62Util() {
    }

    private static final int SCALE = 62;

    private static final char[] BASE_62_ARRAY = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    private static final String BASE_62_CHARACTERS = String.valueOf(BASE_62_ARRAY);


    /**
     * 将long类型编码成Base62字符串
     *
     * @param num num
     * @return java.lang.String
     */
    public static String encode(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.insert(0, BASE_62_ARRAY[(int) (num % SCALE)]);
            num /= SCALE;
        }
        return sb.toString();
    }

    /**
     * 将Base62字符串解码成long类型
     *
     * @param base62Str base62Str
     * @return long
     */
    public static long decode(String base62Str) {
        long num = 0, coefficient = 1;
        String reversedBase62Str = new StringBuilder(base62Str).reverse().toString();
        for (char base62Character : reversedBase62Str.toCharArray()) {
            num += BASE_62_CHARACTERS.indexOf(base62Character) * coefficient;
            coefficient *= SCALE;
        }
        return num;
    }
}
