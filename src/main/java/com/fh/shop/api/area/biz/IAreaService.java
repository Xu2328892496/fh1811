package com.fh.shop.api.area.biz;

import com.fh.shop.api.product.common.ServerException;

public interface IAreaService {

    ServerException queryAreaList(Integer fid);
}
