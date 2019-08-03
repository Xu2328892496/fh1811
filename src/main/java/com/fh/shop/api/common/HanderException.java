package com.fh.shop.api.common;

import com.fh.shop.api.product.common.ResponseEnum;
import com.fh.shop.api.product.common.ServerException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HanderException {

    @ExceptionHandler(GlobalException.class)
    public ServerException handerException(GlobalException ex){
        ResponseEnum responseEnum = ex.get();
        return ServerException.serverException(responseEnum);
    }
}
