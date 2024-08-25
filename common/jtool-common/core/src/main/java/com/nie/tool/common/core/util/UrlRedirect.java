package com.nie.tool.common.core.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * @author njy
 * @since 2024/7/16 11:29
 */
public class UrlRedirect {


    public static void main(String[] args) {
        String originalUrl = "https://www.baidu.com/";

        if (true){
            return;
        }

        if (false){
            return;
        }

        // 24 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0 D
        // wbgxawhawhtkwubalabala


// 输出：e9ac4fbdc398e8c104d1b8415f42cbf8
        System.out.println(Hashing.murmur3_128()
                .hashString(originalUrl, StandardCharsets.UTF_8));
// 输出：06105412
        System.out.println(Hashing.murmur3_32_fixed()
                .hashString(originalUrl, StandardCharsets.UTF_8));
// 输出：bf447182
        System.out.println(Hashing.murmur3_32_fixed()
                .hashLong(Long.MAX_VALUE));

        //
        long l = Hashing.murmur3_32_fixed()
                .hashString("https://blog.csdn.net/lingbomanbu_lyl/article/details/128264414?app_version=5.11.1&code=app_1562916241&csdn_share_tail=%7B%22type%22%3A%22blog%22%2C%22rType%22%3A%22article%22%2C%22rId%22%3A%22128264414%22%2C%22source%22%3A%22lingbomanbu_lyl%22%7D&uLinkId=usr1mkqgl919blen&utm_source=app&_share_channel=copy_link", StandardCharsets.UTF_8)
                .padToLong();
        String s = Base62Util.encode(l);

        System.out.println("l: " + l);
        System.out.println("s: " + s);

        System.out.println(Base62Util.decode(s));
    }

}
