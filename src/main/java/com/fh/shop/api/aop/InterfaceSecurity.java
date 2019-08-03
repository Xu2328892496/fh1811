package com.fh.shop.api.aop;

import com.fh.shop.api.aop.biz.IAopService;
import com.fh.shop.api.aop.po.Aop;
import com.fh.shop.api.common.HardCode;
import com.fh.shop.api.product.common.ResponseEnum;
import com.fh.shop.api.product.common.ServerException;
import com.fh.shop.api.util.RedisKey;
import com.fh.shop.jar.CheckSumBuilder;
import com.fh.shop.jar.RedisAction;
import com.fh.shop.jar.common.ThreadLocalSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class InterfaceSecurity {

    private long dataTime = 60*1000;

    @Resource(name = "aopServiceImpl")
    private IAopService iAopService;

    public Object interfaceSecurity(ProceedingJoinPoint jsonPoint){
        Object proceed = null;
        try {
            //获取客户端提交过来的信息 appSecret
            HttpServletRequest request = ThreadLocalSession.getRequest();
            String appKey = request.getHeader("appKey");
            String nonce = request.getHeader("nonce");
            String time = request.getHeader("time");
            String sign = request.getHeader("sign");
            //判断头信息是否完整
            if (StringUtils.isEmpty(appKey) || StringUtils.isEmpty(sign)
                                            || StringUtils.isEmpty(nonce)
                                            || StringUtils.isEmpty(time)){
                return ServerException.serverException(ResponseEnum.HEADER_INFO);
            }
            //用户请求时间 , 与当前系统时间作对比
            Long headerTime = Long.valueOf(time);
            Long requestTime = System.currentTimeMillis();
            if (requestTime - headerTime > dataTime){
                return ServerException.serverException(ResponseEnum.HEADER_TIME_OUT);
            }
            //判断nonce是否正确
            boolean setNXEX = RedisAction.setNXEX(RedisKey.str(nonce), "1", HardCode.EXPIRE_SECONDS);
            if (!setNXEX){
                return ServerException.serverException(ResponseEnum.HEAVY_ATTACK);
            }
            //验签
            Aop aop = iAopService.queryAop(appKey);
            if (StringUtils.isEmpty(aop)){
                return ServerException.serverException(ResponseEnum.APPKEY_ERROR);
            }
            String checkSum = CheckSumBuilder.getCheckSum(aop.getAppSecret(), nonce, time);
            if (!checkSum.equals(sign)){
                return ServerException.serverException(ResponseEnum.SIGN_ERROR);
            }
            proceed = jsonPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }
}
