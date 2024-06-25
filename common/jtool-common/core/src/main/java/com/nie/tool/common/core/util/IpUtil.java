package com.nie.tool.common.core.util;

import cn.hutool.core.net.Ipv4Util;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @author njy
 * @since 2023/2/23 13:55
 */
public class IpUtil {
    private IpUtil() {
    }

    public static final String IP_URL = "https://whois.pconline.com.cn/ipJson.jsp";

    public static final String UNKNOWN = "未知地址";

    /**
     * getRealAddressByIP
     *
     * @param ip ip
     * @return java.lang.String
     */
    public static String getRealAddressByIP(String ip) {
        if (StrUtil.isBlank(ip)) {
            return UNKNOWN;
        }
        // 内网不查询
        if (Ipv4Util.isInnerIP(ip)) {
            return "内网IP";
        }
        try {
            String res = HttpRequest.get(IP_URL)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .form("json", true)
                    .form("ip", ip)
                    .execute()
                    .body();
            if (StrUtil.isBlank(res)) {
                return UNKNOWN;
            }
            JSONObject jsonNode = JSONUtil.parseObj(res);
            String pro = jsonNode.getStr("pro", "");
            String city = jsonNode.getStr("city", "");
            String region = jsonNode.getStr("region", "");
            String addr = jsonNode.getStr("addr", "");
            return String.format("%s %s %s", pro, city, region);
        } catch (Exception e) {
            return UNKNOWN;
        }
    }


}
