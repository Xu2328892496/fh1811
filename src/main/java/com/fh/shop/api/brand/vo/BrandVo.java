package com.fh.shop.api.brand.vo;

import java.io.Serializable;

public class BrandVo implements Serializable {

    private static final long serialVersionUID = -5255269301539067990L;

    private Integer b_id;

    private String b_name;

    private String b_photo;

    public Integer getB_id() {
        return b_id;
    }

    public BrandVo setB_id(Integer b_id) {
        this.b_id = b_id;
        return this;
    }

    public String getB_name() {
        return b_name;
    }

    public BrandVo setB_name(String b_name) {
        this.b_name = b_name;
        return this;
    }

    public String getB_photo() {
        return b_photo;
    }

    public BrandVo setB_photo(String b_photo) {
        this.b_photo = b_photo;
        return this;
    }
}
