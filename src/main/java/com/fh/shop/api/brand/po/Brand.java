package com.fh.shop.api.brand.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Brand implements Serializable {

    private static final long serialVersionUID = -2894040290449337997L;

    private Integer b_id;

    private String b_name;

    private String b_photo;

    private Date b_createDate;

    private Integer b_sort;

    private Integer b_recommend;

    public Integer getB_id() {
        return b_id;
    }

    public Brand setB_id(Integer b_id) {
        this.b_id = b_id;
        return this;
    }

    public String getB_name() {
        return b_name;
    }

    public Brand setB_name(String b_name) {
        this.b_name = b_name;
        return this;
    }

    public String getB_photo() {
        return b_photo;
    }

    public Brand setB_photo(String b_photo) {
        this.b_photo = b_photo;
        return this;
    }

    public Date getB_createDate() {
        return b_createDate;
    }

    public Brand setB_createDate(Date b_createDate) {
        this.b_createDate = b_createDate;
        return this;
    }

    public Integer getB_sort() {
        return b_sort;
    }

    public Brand setB_sort(Integer b_sort) {
        this.b_sort = b_sort;
        return this;
    }

    public Integer getB_recommend() {
        return b_recommend;
    }

    public Brand setB_recommend(Integer b_recommend) {
        this.b_recommend = b_recommend;
        return this;
    }
}
