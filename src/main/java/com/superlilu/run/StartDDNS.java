package com.superlilu.run;

import com.superlilu.utils.DDNS;

import java.io.File;

public class StartDDNS {
    public static void main(String[] args) {
        File logFile = new File("./logs");
        if (!logFile.exists()) {
            boolean mkdir = logFile.mkdir();
            if (mkdir) {
                System.out.println("创建日志文件夹成功");
            } else {
                System.out.println("创建日志文件夹失败");
            }
        }
        DDNS.ddns();
    }
}
