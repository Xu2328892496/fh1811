package com.fh.shop.api.product.po;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {

    private static final long serialVersionUID = -2974596024218041615L;

    private Integer id;

    private String name;

    private Float price;

    private String classifyid;

    private Date createDate;

    private String photoName;

    private Integer gc1;

    private Integer gc2;

    private Integer gc3;

    private String classifyName;

    private Integer hotSell;

    public Integer getHotSell() {
        return hotSell;
    }

    public Product setHotSell(Integer hotSell) {
        this.hotSell = hotSell;
        return this;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public Product setClassifyName(String classifyName) {
        this.classifyName = classifyName;
        return this;
    }

    public String getClassifyid() {
        return classifyid;
    }

    public Product setClassifyid(String classifyid) {
        this.classifyid = classifyid;
        return this;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getGc1() {
        return gc1;
    }

    public Product setGc1(Integer gc1) {
        this.gc1 = gc1;
        return this;
    }

    public Integer getGc2() {
        return gc2;
    }

    public Product setGc2(Integer gc2) {
        this.gc2 = gc2;
        return this;
    }

    public Integer getGc3() {
        return gc3;
    }

    public Product setGc3(Integer gc3) {
        this.gc3 = gc3;
        return this;
    }
}
