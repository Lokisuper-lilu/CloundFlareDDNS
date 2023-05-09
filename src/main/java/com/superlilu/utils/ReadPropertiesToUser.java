package com.superlilu.utils;

import com.superlilu.pojo.UserInfo;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class ReadPropertiesToUser {


    private static final ReadPropertiesToUser readPo = new ReadPropertiesToUser();
    private UserInfo userInfo;
    private static final Logger logger = Logger.getLogger(ReadPropertiesToUser.class);

    private ReadPropertiesToUser() {
    }

    public static ReadPropertiesToUser getReadPo() {
        return readPo;
    }

    public UserInfo getUserInfo() {
        if (userInfo == null) {
            logger.debug("userInfo为空");
            logger.info("准备读取配置文件");
            readProperties();
        }
        return userInfo;
    }

    private void setUserInfos() {
        UserInfo userInfo;
        //读取配置文件
        Properties properties = new Properties();
        logger.debug("开始读取配置文件");
        try {
            //读取路径为./user.properties的配置文件
            File propFile = new File("./user.properties");
            //读取propFile文件
            properties.load(new FileInputStream(propFile));
            logger.debug("读取配置文件成功");
            userInfo = new UserInfo();
            logger.debug("开始设置用户信息");
            userInfo.setEmail(properties.getProperty("email"));
            userInfo.setKey(properties.getProperty("key"));
            userInfo.setZone_id(properties.getProperty("zone_id"));
            userInfo.setRecord_id(properties.getProperty("record_id"));
            userInfo.setType(properties.getProperty("type"));
            userInfo.setName(properties.getProperty("name"));
            userInfo.setForce(properties.getProperty("force").equals("true"));
            if (properties.getProperty("ttl") != null) {
                logger.debug("ttl不为空,设置ttl为" + properties.getProperty("ttl"));
                userInfo.setTtl(Integer.parseInt(properties.getProperty("ttl")));
            } else {
                logger.debug("ttl为空,设置ttl为120");
            }
            if (properties.getProperty("select") != null) {
                logger.debug("select不为空,select为" + properties.getProperty("select"));
                userInfo.setSelect(Boolean.parseBoolean(properties.getProperty("select")));
                if (!userInfo.isSelect()) {
                    logger.debug("select为False,设置为对目标域名ddns");
                    userInfo.setObjectHost(properties.getProperty("ObjectHost"));
                }
            }
            readPo.setUserInfo(userInfo);
            logger.debug("设置用户信息成功");
        }//若Properties不存在
        catch (FileNotFoundException e) {
            logger.error("配置文件不存在");
            //创建配置文件
            File file = new File("./user.properties");
            boolean success = false;
            try {
                success = file.createNewFile();
            } catch (IOException ex) {
                logger.error("创建配置文件失败");
            }
            if (!success) {
                logger.error("创建配置文件失败");
                System.exit(1);
            }
            //准备写入配置文件
            Properties properties1 = new Properties();
            properties1.setProperty("email", "");
            properties1.setProperty("key", "");
            properties1.setProperty("zone_id", "");
            properties1.setProperty("record_id", "");
            properties1.setProperty("type", "");
            properties1.setProperty("name", "");
            properties1.setProperty("ttl", "");
            properties1.setProperty("force", "false");
            //写入配置文件
            try {
                properties1.store(new FileOutputStream(file), "user.properties");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            logger.info("创建配置文件成功");
            logger.info("请填写配置文件");
            System.exit(0);
        } catch (Exception e) {
            logger.error("发生异常,异常信息为:" + e.getMessage());
            logger.error("异常cause为:" + e.getCause());
            e.printStackTrace();
        }
    }

    public void readProperties() {
        setUserInfos();
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
