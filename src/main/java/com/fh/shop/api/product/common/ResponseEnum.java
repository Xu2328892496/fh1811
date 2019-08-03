package com.fh.shop.api.product.common;

public enum ResponseEnum {

    SUCCESS(200 , "成功"),

    ERROR(201 , "错误"),

    SMS_PHONE_IS_EMPTY(3000 , "手机号不能为空"),

    SMS_PHONE_ERROR(3002 , "手机号以13、15、18开头 , 且长度为11位"),

    SMS_RESULT_CODE_ERROR(3003 , "调用网易云信接口失败"),

    SMS_CODE_ITERATE(3004 , "60秒内不能重复发送验证码"),

    SMS_CODE_TIMEOUT(3005 , "验证码已过期"),

    SMS_CODE_ERROR(3006 , "验证码错误"),

    MEMBER_EXIST(401 , "会员名已存在"),

    MEMBER_NOTEMPTY(1000 , "会员名或密码不能为空"),

    MEMBERNAME_ERROR(1001 , "会员名不正确"),

    MEMBERPASSWD_ERROR(1002 , "密码不正确"),

    HEADER_INEXISTENCE(2001 , "header头信息不存在"),

    HEADER_TAMPER(2002 , "header头信息被篡改"),

    LOGIN_TIMEOUT(2003 , "连接超时"),

    MEMBER_DEAD(2004 , "会员停用 , 请联系客服"),

    MEMBER_NAME_IS_EMPTY(4000 , "会员名不能为空"),

    MEMBER_REALNAME_IS_EMPTY(4001 , "真实姓名不能为空"),

    MEMBER_PASSWD_IS_EMPTY(4002 , "密码不能为空"),

    MEMBER_BIRTHDAY_IS_EMPTY(4003 , "生日不能为空"),

    MEMBER_PHONE_IS_EMPTY(4004 , "手机号不能为空"),

    MEMBER_CODE_IS_EMPTY(4005 , "验证码不能为空"),

    MEMBER_EMILY_IS_EMPTY(4006 , "邮箱不能为空"),

    MEMBER_A1_IS_EMPTY(4007 , "省不能为空"),

    MEMBER_A2_IS_EMPTY(4008 , "市不能为空"),

    MEMBER_A3_IS_EMPTY(4009 , "县不能为空"),

    MEMBER_REALNAME_ISCHINESE(4010 , "真实姓名必须为中文"),

    MEMBER_IS_EMPTY(4011 , "会员信息不能为空"),

    HEADER_INFO(5000 , "头信息不完整"),

    HEADER_TIME_OUT(5001 , "已超时"),

    HEAVY_ATTACK(5002 , "重攻击"),

    SIGN_ERROR(5003 , "验签失败,内容被篡改"),

    APPKEY_ERROR(5004 , "appkey不正确");

    private int code;

    private String message;

    private ResponseEnum(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
