package com.nie.tool.common.core.util.context;


import com.nie.tool.common.core.enums.I18nEnum;
import com.nie.tool.common.core.util.StrUtil;

/**
 * @author njy
 * @since 2024/8/22 10:32
 */
public class I18nContextUtil {
    private static final String   I18N_KEY      = "lang";
    private static final I18nEnum defaultLocale = I18nEnum.CN;

    static {
        ContextHolderUtil.register(() -> I18N_KEY);
    }

    public static String getI18nContextKey() {
        return I18N_KEY;
    }


    public static void setLang(String headerLangName) {
        I18nEnum i18nEnum = I18nEnum.parseByHeaderName(headerLangName);
        ContextHolderUtil.set(getI18nContextKey(), i18nEnum.getHeaderName());
    }

    public static String getLang() {
        String lang = ContextHolderUtil.get(getI18nContextKey());
        if (StrUtil.isNotEmpty(lang)) {
            return lang;
        }
        return defaultLocale.getHeaderName();
    }

    public static void removeLang() {
        ContextHolderUtil.remove(getI18nContextKey());
    }
}
