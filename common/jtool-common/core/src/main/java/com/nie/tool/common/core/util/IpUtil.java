package com.nie.tool.common.core.util;

import com.nie.tool.common.core.util.execute.ExecuteUtil;
import org.dromara.hutool.core.net.Ipv4Util;
import org.dromara.hutool.http.HttpUtil;
import org.dromara.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author njy
 * @since 2023/2/23 13:55
 */
public class IpUtil {
    private IpUtil() {
    }

    public static final String IP_URL = "https://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";

    public static IpAddressDetail getIpAddressDetail(String ipv4) {
        return getIpAddressDetail(ipv4, false);
    }


    @Deprecated(since = "2023-01-01")
    public static IpAddressDetail getIpAddressDetail(String ipv4, boolean throwErr) {
        if (Ipv4Util.isInnerIP(ipv4)) {
            return new IpAddressDetail(null);
        }
        return ExecuteUtil.supply(() -> {
            String res = HttpUtil.get(String.format(IP_URL, ipv4));
            return new IpAddressDetail(JSONUtil.parseObj(res));
        }, throwErr, new IpAddressDetail(null), "IpUtil.getIpAddressDetail()", new HashMap<>() {{
            put("ipv4", ipv4);
            put("throwErr", throwErr);
        }});
    }


    public static void main(String[] args) {
        //tnsltnslslwbslwb..htk
        System.out.println(getIpAddressDetail("60.186.51.5"));

        IpAddressDetail ipAddressDetail = getIpAddressDetail("127.0.0.1");
        System.out.println(ipAddressDetail.addr == null);

    }


    public static class IpAddressDetail {
        public final String ip;
        public final String pro;
        public final String proCode;
        public final String city;
        public final String cityCode;
        public final String region;
        public final String regionCode;
        public final String addr;
        public final String regionNames;
        public final String err;

        public IpAddressDetail(Map<String, Object> map) {
            ip = MapUtil.getStr(map, "ip");
            pro = MapUtil.getStr(map, "pro");
            proCode = MapUtil.getStr(map, "proCode");
            city = MapUtil.getStr(map, "city");
            cityCode = MapUtil.getStr(map, "cityCode");
            region = MapUtil.getStr(map, "region");
            regionCode = MapUtil.getStr(map, "regionCode");
            addr = MapUtil.getStr(map, "addr");
            regionNames = MapUtil.getStr(map, "regionNames");
            err = MapUtil.getStr(map, "err");
        }

        @Override
        public String toString() {
            return "IpAddressDetail{" +
                    "ip='" + ip + '\'' +
                    ", pro='" + pro + '\'' +
                    ", proCode='" + proCode + '\'' +
                    ", city='" + city + '\'' +
                    ", cityCode='" + cityCode + '\'' +
                    ", region='" + region + '\'' +
                    ", regionCode='" + regionCode + '\'' +
                    ", addr='" + addr + '\'' +
                    ", regionNames='" + regionNames + '\'' +
                    ", err='" + err + '\'' +
                    '}';
        }
    }

}
