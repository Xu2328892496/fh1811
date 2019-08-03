package com.fh.shop.api.aop.biz;

import com.fh.shop.api.aop.mapper.IAopMapper;
import com.fh.shop.api.aop.po.Aop;
import com.fh.shop.jar.RedisAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("aopServiceImpl")
public class AopServiceImpl implements IAopService{

    @Autowired
    private IAopMapper iAopMapper;

    public Aop queryAop(String appKey) {
        String value = RedisAction.get(appKey);
        Aop aop = null;
        if (StringUtils.isEmpty(value)){
            aop = iAopMapper.queryAop(appKey);
            if (!StringUtils.isEmpty(aop)){
                RedisAction.setex(appKey , aop.getAppSecret() , 30*60);
            }
        }
        return aop;
    }
}
