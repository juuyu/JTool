package com.nie.tool.common.core.util;

import com.nie.tool.common.core.util.execute.ExecuteUtil;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * @author njy
 * @since 2024/6/24 16:06
 */
public class ChineseCharUtil {

    private ChineseCharUtil() {
    }

    private static final HanyuPinyinOutputFormat DEFAULT_FORMAT = new HanyuPinyinOutputFormat();

    private static final char CHINESE_START = '\u4E00'; // 19968
    private static final char CHINESE_END   = '\u9FFF'; // 40959


    static {
        DEFAULT_FORMAT.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        DEFAULT_FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    /**
     * getChineseCharStart
     *
     * @return char
     */
    public static char getChineseCharStart() {
        return CHINESE_START;
    }

    /**
     * getChineseCharEnd
     *
     * @return char
     */
    public static char getChineseCharEnd() {
        return CHINESE_END;
    }

    /**
     * isChineseChar
     *
     * @param c c
     * @return boolean
     */
    public static boolean isChineseChar(char c) {
        return c >= CHINESE_START && c <= CHINESE_END;
    }


    /**
     * retainChineseCharacters
     *
     * @param str str
     * @return java.lang.String
     */
    public static String retainChineseCharacters(String str) {
        if (StrUtil.isBlank(str)) {
            return StrUtil.EMPTY;
        }

        StringBuilder chineseCharacters = new StringBuilder();
        for (char c : str.trim().toCharArray()) {
            if (isChineseChar(c)) {
                chineseCharacters.append(c);
            }
        }
        return chineseCharacters.toString();
    }


    /**
     * toPinyin
     *
     * @param chinese chinese
     * @return java.lang.String
     */
    public static String toPinyin(char chinese) {
        if (!isChineseChar(chinese)) {
            return String.valueOf(chinese);
        }
        return ExecuteUtil.supplyIf(() -> PinyinHelper.toHanyuPinyinStringArray(chinese, DEFAULT_FORMAT)[0],
                isChineseChar(chinese), String.valueOf(chinese));
    }

    /**
     * toPinyin
     *
     * @param chinese chinese
     * @return java.lang.String
     */
    public static String toPinyin(String chinese) {
        return toPinyin(chinese, false);
    }

    /**
     * toPinyin
     *
     * @param chinese           chinese
     * @param retainChineseOnly retainChineseOnly
     * @return java.lang.String
     */
    public static String toPinyin(String chinese, boolean retainChineseOnly) {
        if (StrUtil.isBlank(chinese)) {
            return StrUtil.EMPTY;
        }

        if (retainChineseOnly) {
            chinese = retainChineseCharacters(chinese);
        }

        StringBuilder pinyinStr = new StringBuilder();

        for (char c : chinese.toCharArray()) {
            pinyinStr.append(toPinyin(c));
        }

        return pinyinStr.toString();
    }

}
