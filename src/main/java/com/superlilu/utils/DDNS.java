package com.superlilu.utils;

import com.superlilu.pojo.UserInfo;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class DDNS {
    static String ip;
    private static final Logger DDNSlogger = Logger.getLogger(DDNS.class);

    public static void ddns() {
        ReadPropertiesToUser readPo = ReadPropertiesToUser.getReadPo();
        UserInfo userInfo = readPo.getUserInfo();
        System.out.println(userInfo);
        if (userInfo.getZone_id().equals("")) {
            DDNSlogger.error("zone_id为空,请检查配置文件");
            return;
        }
        if (userInfo.getRecord_id().equals("")) {
            DDNSlogger.error("record_id为空,请检查配置文件");
            return;
        }
        if (userInfo.getName().equals("")) {
            DDNSlogger.error("域名为空,请检查配置文件");
            return;
        }
        if (userInfo.getKey().equals("")) {
            DDNSlogger.error("key为空,请检查配置文件");
            return;
        }
        if (userInfo.getType().equals("")) {
            DDNSlogger.error("type为空,请检查配置文件");
            return;
        }
        if (userInfo.getEmail().equals("")) {
            DDNSlogger.error("email为空,请检查配置文件");
            return;
        }
        String apiUrl = DDNSUtils.getApiUrl(userInfo.getZone_id(), userInfo.getRecord_id());
        if (userInfo.isSelect()) {
            if (userInfo.getType().equals("A")) {
                ip = DDNSUtils.getLocalIPv4();
            } else {
                ip = DDNSUtils.getLocalIPv6();
            }
        } else {
            ip = DDNSUtils.getIP(userInfo.getObjectHost());
        }
        String ip2 = DDNSUtils.getIP(userInfo.getName());
        if (ip.equals(ip2)) {
            DDNSlogger.info("IP地址一致，无需更新");
            return;
        }
        if (ip2.equals("0.0.0.0")) {
            DDNSlogger.error("获取IP地址失败,请检查网络连接,或者检查域名是否正确");
            return;
        }

        String jsonBody = String.format("{\"type\":\"%s\",\"name\":\"%s\",\"content\":\"%s\",\"ttl\":%d,\"proxied\":false}"
                , userInfo.getType(), userInfo.getName(), ip, userInfo.getTtl());
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Auth-Email", userInfo.getEmail());
            connection.setRequestProperty("X-Auth-Key", userInfo.getKey());
            connection.setDoOutput(true);
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.write(jsonBody.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = connection.getResponseCode();

            String responseBody;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                responseBody = in.lines().collect(Collectors.joining());
            }
            DDNSlogger.info("更新结果：" + responseBody);
            if (responseCode == 200) {
                DDNSlogger.info("更新成功");
            } else {
                DDNSlogger.error("更新失败");
            }
            List<String> contentTypes = connection.getHeaderFields().get("Content-Type");
            DDNSlogger.info("Content-Type response header value: " + contentTypes.get(0));
        } catch (IOException e) {
            DDNSlogger.error("IO异常" + e.getMessage());
        }
    }
}