package com.fh.shop.api.test;

import com.fh.shop.api.product.biz.IProductService;
import com.fh.shop.jar.CheckSumBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-common.xml"})
public class test {
    
    @Autowired
    private IProductService iProductService;
    
    @Test
    public void test(){
        long time = new Date().getTime();
        String nonce = UUID.randomUUID().toString().replace("-","");
        String sign = CheckSumBuilder.getCheckSum("4234", nonce, time + "");
        System.out.println(time);
        System.out.println(nonce);
        System.out.println(sign);
    }
}
