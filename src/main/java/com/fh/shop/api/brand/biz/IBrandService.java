package com.fh.shop.api.brand.biz;

import com.fh.shop.api.product.common.ServerException;

public interface IBrandService {
    //查询品牌列表
    ServerException queryBrandList();
}
