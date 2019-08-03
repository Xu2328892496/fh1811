package com.fh.shop.api.area.biz;

import com.fh.shop.api.area.mapper.IAreaMapper;
import com.fh.shop.api.area.po.Area;
import com.fh.shop.api.product.common.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("areaServiceImpl")
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private IAreaMapper iAreaMapper;

    public ServerException queryAreaList(Integer fid) {
        List<Area> areaList = iAreaMapper.queryAreaList(fid);
        return ServerException.serverException(areaList);
    }
}
