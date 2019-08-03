package com.fh.shop.api.area.mapper;

import com.fh.shop.api.area.po.Area;

import java.util.List;

public interface IAreaMapper {

    List<Area> queryAreaList(Integer fid);
}
