package com.fh.shop.api.sms.biz;

import com.fh.shop.api.product.common.ServerException;

public interface ISmsService {

    //发送短信
    ServerException sendSms(String phone);
}
