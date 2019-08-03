package com.fh.shop.api.sms.controller;

import com.fh.shop.api.product.common.ServerException;
import com.fh.shop.api.sms.biz.ISmsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Resource(name = "smsServiceImpl")
    private ISmsService iSmsService;

    //发送短信
    @RequestMapping(value = "sendSms" , method = RequestMethod.GET)
    public ServerException sendSms(String phone){
        return iSmsService.sendSms(phone);
    }
}
