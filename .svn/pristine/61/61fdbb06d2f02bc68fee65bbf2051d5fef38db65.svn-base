package com.fh.shop.api.product.common;

public class ServerException {

    private int code;

    private String message;

    private Object data;

    public ServerException(){

    }

    private ServerException(int code,String message,Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ServerException serverException(){
        return new ServerException(ResponseEnum.SUCCESS.getCode() , ResponseEnum.SUCCESS.getMessage() , null);
    }

    public static ServerException serverException(Object data){
        return new ServerException(ResponseEnum.SUCCESS.getCode() , ResponseEnum.SUCCESS.getMessage() , data);
    }

    public static ServerException serverException(ResponseEnum responseEnum){
        return new ServerException(responseEnum.getCode() , responseEnum.getMessage() , null);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public ServerException setCode(int code) {
        this.code = code;
        return this;
    }

    public ServerException setMessage(String message) {
        this.message = message;
        return this;
    }

    public ServerException setData(Object data) {
        this.data = data;
        return this;
    }
}
