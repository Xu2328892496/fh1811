package com.fh.shop.api.aop.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Aop implements Serializable {

    private static final long serialVersionUID = -3156296744620156272L;

    private String appKey;

    private String appSecret;
}
