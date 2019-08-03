package com.fh.shop.api.common;

import com.fh.shop.api.product.common.ResponseEnum;

public class GlobalException extends RuntimeException {

    private ResponseEnum responseEnum;

    public GlobalException(ResponseEnum re){
        this.responseEnum = re;
    }

    public ResponseEnum get(){
        return this.responseEnum;
    }
}
