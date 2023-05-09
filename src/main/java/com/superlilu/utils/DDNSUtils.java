package com.superlilu.utils;

import com.superlilu.pojo.UserInfo;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

import java.net.*;

@SuppressWarnings("unused")
public class DDNSUtils {
    private static final Logger logger = Logger.getLogger(DDNSUtils.class);

    // 获取一个域名的IP地址
    public static String getIP(String hostname) {
        InetAddress address;
        try {
            address = InetAddress.getByName(hostname);
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("获取域名IP失败,域名为:" + hostname + ",异常信息为:" + e.getMessage());
        }
        return "0.0.0.0";
    }// 获取一个域名的IP地址

    // 获取API的URL
    public static String getApiUrl(String zone, String record) {
        UserInfo userInfo = new UserInfo();
        return "https://api.cloudflare.com/client/v4/zones/" +
                zone + "/dns_records/" + record;
    }// 获取API的URL

    //用Jsoup访问https://ipv4.icanhazip.com/获取本机IPv4地址
    public static String getLocalIPv4() {
        logger.info("正在获取本机IPv4地址");
        String url = "https://ipv4.icanhazip.com/";
        String localIpv4 = null;
        try {
            localIpv4 = Jsoup.connect(url).ignoreContentType(true).execute().body();
            localIpv4.replaceAll("\n", "");
        } catch (Exception e) {
            logger.error("获取本机IPv4地址失败,可能是网络问题");
            logger.error("错误信息为" + e.getMessage());
        }
        logger.info("本机IPv4地址为：" + localIpv4);
        return localIpv4;
    }//用Jsoup访问https://ipv4.icanhazip.com/获取本机IPv4地址

    //用Jsoup访问https://ipv6.icanhazip.com/获取本机IPv6地址
    public static String getLocalIPv6() {
        logger.info("正在获取本机IPv6地址");
        String localIPv6 = null;
        String url = "https://ipv6.icanhazip.com/";
        try {
            localIPv6 = Jsoup.connect(url).ignoreContentType(true).execute().body();
        } catch (Exception e) {
            logger.error("获取本机IPv6地址失败,可能是网络问题,或者本机没有IPv6地址");
            logger.error("错误信息为" + e.getMessage());
            System.exit(0);
        }
        localIPv6 = localIPv6.replaceAll("\n", "");
        logger.info("本机IPv6地址为：" + localIPv6);
        return localIPv6;
    }//用Jsoup访问https://ipv6.icanhazip.com/获取本机IPv6地址
}

