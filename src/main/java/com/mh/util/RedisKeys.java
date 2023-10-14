package com.mh.util;

/**
 * @author zhouxp
 * @create 2019-06-30 11:34
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key){
        return "sessionid:" + key;
    }
}
