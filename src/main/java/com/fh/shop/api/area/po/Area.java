package com.fh.shop.api.area.po;

import java.io.Serializable;

public class Area implements Serializable {

    private static final long serialVersionUID = 8114094375407136039L;

    private Integer id;

    private String areaName;

    private Integer fid;

    public Integer getId() {
        return id;
    }

    public Area setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAreaName() {
        return areaName;
    }

    public Area setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    public Integer getFid() {
        return fid;
    }

    public Area setFid(Integer fid) {
        this.fid = fid;
        return this;
    }
}
