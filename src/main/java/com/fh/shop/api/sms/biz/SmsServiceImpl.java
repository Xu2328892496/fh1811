package com.fh.shop.api.sms.biz;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.api.common.HardCode;
import com.fh.shop.api.product.common.ResponseEnum;
import com.fh.shop.api.product.common.ServerException;
import com.fh.shop.api.sms.vo.SmsCode;
import com.fh.shop.api.util.RedisKey;
import com.fh.shop.jar.RedisAction;
import com.fh.shop.jar.SMSUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service("smsServiceImpl")
public class SmsServiceImpl implements ISmsService {

    //发送短信
    public ServerException sendSms(String phone) {
        //手机号的非空验证
        if (StringUtils.isEmpty(phone)){
            return ServerException.serverException(ResponseEnum.SMS_PHONE_IS_EMPTY);
        }
        //手机的正则验证
        if (!Pattern.matches(HardCode.PHONE_REGEX , phone)){
            return ServerException.serverException(ResponseEnum.SMS_PHONE_ERROR);
        }
        //判断验证是否过期
        boolean exist = RedisAction.exist(RedisKey.smsCode(phone));
        if (exist){
            return ServerException.serverException(ResponseEnum.SMS_CODE_ITERATE);
        }
        //发送短信
        String str = SMSUtil.smsResult(phone);
        //将返回的json格式的字符串转换为java对象
        SmsCode smsCode = JSONObject.parseObject(str, SmsCode.class);
        //判断调用网易云信接口是否成功
        if (smsCode.getCode() != HardCode.SMS_RESULT_CODE){
            return ServerException.serverException(ResponseEnum.SMS_RESULT_CODE_ERROR);
        }
        //将手机号当做key , 验证码当value , 存放redis中并设置存活时间
        RedisAction.setex(RedisKey.smsCode(phone) , smsCode.getObj() , HardCode.SMS_CODE_LIFE_TIME);
        return ServerException.serverException();
    }


}
