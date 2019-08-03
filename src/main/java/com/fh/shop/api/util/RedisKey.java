package com.fh.shop.api.util;

public class RedisKey {

    public static String redisKey(String memberName , String uuid){
        return "user:"+memberName+":"+uuid;
    }

    public static String smsCode(String phone){
        return "member:smsCode:"+phone;
    }

    public static String str(String name){
        return "code:"+name;
    }
}
